package com.sunmi.sunbay.nexus.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.sunmi.sunbay.nexus.constant.ApiConstants;
import com.sunmi.sunbay.nexus.exception.SunbayBusinessException;
import com.sunmi.sunbay.nexus.exception.SunbayNetworkException;
import com.sunmi.sunbay.nexus.model.common.BaseResponse;
import com.sunmi.sunbay.nexus.util.IdGenerator;
import com.sunmi.sunbay.nexus.util.JsonUtil;
import com.sunmi.sunbay.nexus.util.UserAgentUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;

import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * HTTP client for Sunbay API
 * <p>
 * This client implements AutoCloseable to properly manage HTTP resources.
 * </p>
 *
 * @since 2025-12-10
 */
@Slf4j
public class HttpClient implements AutoCloseable {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_REQUEST_ID = "X-Client-Request-Id";
    private static final String HEADER_TIMESTAMP = "X-Timestamp";
    private static final String HEADER_USER_AGENT = "User-Agent";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final long RETRY_DELAY_BASE_MS = 1000L;
    private static final long CONNECTION_TIME_TO_LIVE_SECONDS = 300L;
    private static final int DEFAULT_CONNECTION_REQUEST_TIMEOUT = 5000;
    private static final int VALIDATE_AFTER_INACTIVITY_MS = 2000;

    /**
     * HTTP status codes that indicate a transient server-side condition where an
     * immediate retry (with backoff) has a reasonable chance of succeeding.
     */
    private static final int HTTP_STATUS_REQUEST_TIMEOUT = 408;
    private static final int HTTP_STATUS_TOO_MANY_REQUESTS = 429;
    private static final int HTTP_STATUS_BAD_GATEWAY = 502;
    private static final int HTTP_STATUS_SERVICE_UNAVAILABLE = 503;
    private static final int HTTP_STATUS_GATEWAY_TIMEOUT = 504;

    /**
     * JavaBean getter prefixes recognized when projecting a request object into
     * a URL query string. {@code get} covers all types; {@code is} is only
     * accepted for boolean / Boolean returns to match Lombok conventions.
     */
    private static final String GETTER_PREFIX = "get";
    private static final String BOOLEAN_GETTER_PREFIX = "is";
    private static final String GETCLASS_METHOD_NAME = "getClass";

    /**
     * Per-class cache of resolved query-parameter getters. Populated lazily the
     * first time a given request DTO type is projected into a URL query string.
     * A request class's public method set is immutable, so this cache is safe to
     * hold for the lifetime of the JVM and shared across all HttpClient instances.
     */
    private static final ConcurrentMap<Class<?>, List<Method>> QUERY_PARAM_GETTER_CACHE =
            new ConcurrentHashMap<>();

    private final String apiKey;
    private final String baseUrl;
    private final CloseableHttpClient httpClient;
    private final PoolingHttpClientConnectionManager connectionManager;
    private final int maxRetries;

    public HttpClient(String apiKey, String baseUrl, int connectTimeout, int readTimeout, int maxRetries) {
        this(apiKey, baseUrl, connectTimeout, readTimeout, maxRetries, null, null);
    }

    public HttpClient(String apiKey, String baseUrl, int connectTimeout, int readTimeout, int maxRetries,
                      Integer maxTotal, Integer maxPerRoute) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.maxRetries = maxRetries;

        // In HttpClient 5.x, connect timeout and TTL live on ConnectionConfig at the pool level;
        // response (read) timeout lives on RequestConfig as setResponseTimeout.
        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setConnectTimeout(Timeout.ofMilliseconds(connectTimeout))
                .setSocketTimeout(Timeout.ofMilliseconds(readTimeout))
                .setTimeToLive(TimeValue.ofSeconds(CONNECTION_TIME_TO_LIVE_SECONDS))
                .setValidateAfterInactivity(TimeValue.ofMilliseconds(VALIDATE_AFTER_INACTIVITY_MS))
                .build();

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(DEFAULT_CONNECTION_REQUEST_TIMEOUT))
                .setResponseTimeout(Timeout.ofMilliseconds(readTimeout))
                .build();

        this.connectionManager = new PoolingHttpClientConnectionManager();
        this.connectionManager.setDefaultConnectionConfig(connectionConfig);
        if (maxTotal != null) {
            connectionManager.setMaxTotal(maxTotal);
        }
        // Default maxPerRoute to maxTotal for single-host SDK scenario
        connectionManager.setDefaultMaxPerRoute(maxPerRoute != null ? maxPerRoute : connectionManager.getMaxTotal());

        this.httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                // Disable Apache's built-in retry so it does not stack with the SDK's own retry
                // loop in executeGetWithRetry. Retry policy is centralized at the SDK layer.
                .disableAutomaticRetries()
                .evictExpiredConnections()
                .evictIdleConnections(TimeValue.ofSeconds(60L))
                .build();
    }

    /**
     * Execute POST request
     *
     * @param path         API path
     * @param requestBody  request body object
     * @param responseType response type class
     * @param <T>          response type
     * @return response object
     */
    public <T extends BaseResponse> T post(String path, Object requestBody, Class<T> responseType) {
        String url = baseUrl + path;
        String requestJson = JsonUtil.toJson(requestBody);

        HttpPost httpPost = new HttpPost(url);
        // ContentType.APPLICATION_JSON already carries the UTF-8 charset and application/json
        // media type, so the entity itself declares Content-Type on the wire.
        httpPost.setEntity(new StringEntity(requestJson, ContentType.APPLICATION_JSON));

        addCommonHeaders(httpPost, ApiConstants.HTTP_METHOD_POST);

        return doExecute(httpPost, requestJson, responseType);
    }

    /**
     * Execute GET request
     *
     * @param path         API path
     * @param request      request object with query parameters
     * @param responseType response type class
     * @param <T>          response type
     * @return response object
     */
    public <T extends BaseResponse> T get(String path, Object request, Class<T> responseType) {
        try {
            URI uri = buildGetUri(path, request);
            return executeGetWithRetry(uri, responseType);
        } catch (URISyntaxException e) {
            // URI construction failure means the caller-supplied baseUrl (or a request
            // field used as a query parameter) is malformed. This is a programming /
            // configuration error, not a network condition.
            throw new IllegalArgumentException("Invalid URL: " + e.getMessage(), e);
        }
    }

    /**
     * Build URI with query parameters from request object.
     * <p>
     * Walks the JavaBean-style getters ({@code getFoo()} and, for booleans,
     * {@code isFoo()}) of the request object and adds each non-null value as a
     * query parameter. Only "simple" scalar types (String / Number / Boolean /
     * Enum) are supported for GET query parameters. Complex types (nested
     * objects, collections, maps, dates) or getters that throw are logged at
     * WARN and skipped rather than serialized via {@code toString()}, which
     * would silently send garbage to the server.
     */
    private URI buildGetUri(String path, Object request) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(baseUrl + path);
        if (request == null) {
            return uriBuilder.build();
        }

        Class<?> requestClass = request.getClass();
        List<Method> getters = QUERY_PARAM_GETTER_CACHE.computeIfAbsent(
                requestClass, HttpClient::resolveQueryParamGetters);
        for (Method method : getters) {
            Object value;
            try {
                value = method.invoke(request);
            } catch (ReflectiveOperationException e) {
                log.warn("Skipping GET query parameter for {}.{}() - getter threw: {}",
                        requestClass.getSimpleName(), method.getName(), e.toString());
                continue;
            }
            if (value == null) {
                continue;
            }
            if (!isSimpleQueryValue(value)) {
                log.warn("Skipping GET query parameter for {}.{}() - unsupported type {} for URL query string",
                        requestClass.getSimpleName(), method.getName(),
                        value.getClass().getName());
                continue;
            }
            String paramName = convertMethodNameToParamName(method.getName());
            uriBuilder.addParameter(paramName, String.valueOf(value));
        }
        return uriBuilder.build();
    }

    /**
     * Walk the public methods of a request class once and return the ones that
     * qualify as query-parameter getters. Feeds {@link #QUERY_PARAM_GETTER_CACHE}.
     */
    private static List<Method> resolveQueryParamGetters(Class<?> requestClass) {
        List<Method> getters = new ArrayList<>();
        for (Method method : requestClass.getMethods()) {
            if (isQueryParamGetter(method)) {
                getters.add(method);
            }
        }
        return getters;
    }

    /**
     * Whether the given method looks like a JavaBean getter that should be
     * projected into the query string. Zero-arg, name starts with {@code get}
     * (any return type) or {@code is} (only for boolean / Boolean returns).
     * {@link Object#getClass()} is explicitly excluded.
     */
    private static boolean isQueryParamGetter(Method method) {
        if (method.getParameterCount() != 0) {
            return false;
        }
        String name = method.getName();
        if (GETCLASS_METHOD_NAME.equals(name)) {
            return false;
        }
        if (name.startsWith(GETTER_PREFIX) && name.length() > GETTER_PREFIX.length()) {
            return true;
        }
        if (name.startsWith(BOOLEAN_GETTER_PREFIX) && name.length() > BOOLEAN_GETTER_PREFIX.length()) {
            Class<?> returnType = method.getReturnType();
            return returnType == boolean.class || returnType == Boolean.class;
        }
        return false;
    }

    /**
     * Whether the given value can be safely rendered as a URL query parameter
     * via {@code String.valueOf(value)}. Anything else (POJOs, collections,
     * maps, dates, ...) would produce a caller-hostile string.
     */
    private static boolean isSimpleQueryValue(Object value) {
        return value instanceof CharSequence
                || value instanceof Number
                || value instanceof Boolean
                || value instanceof Character
                || value instanceof Enum<?>;
    }

    /**
     * Execute GET request with retry, creating a fresh HttpGet on each attempt
     * to avoid reusing an already-executed request object
     */
    @SuppressWarnings("deprecation")
    private <T extends BaseResponse> T executeGetWithRetry(URI uri, Class<T> responseType) {
        int attempts = 0;
        int maxAttempts = maxRetries;

        while (attempts < maxAttempts) {
            attempts++;
            try {
                HttpGet httpGet = new HttpGet(uri);
                addCommonHeaders(httpGet, ApiConstants.HTTP_METHOD_GET);
                return doExecute(httpGet, null, responseType);
            } catch (SunbayNetworkException e) {
                // Give up immediately on errors that will not improve on retry
                // (bad host, refused connection, TLS handshake, business errors, ...).
                if (!e.isRetryable()) {
                    if (log.isDebugEnabled()) {
                        log.debug("Request failed with non-retryable error, aborting: {}", e.getMessage());
                    }
                    throw e;
                }
                if (attempts >= maxAttempts) {
                    if (log.isDebugEnabled()) {
                        log.debug("Request failed after {} attempts: {}", attempts, e.getMessage());
                    }
                    throw e;
                }
                if (log.isDebugEnabled()) {
                    log.debug("Request failed, retrying ({}/{}) after delay: {}", attempts, maxAttempts, e.getMessage());
                }
                try {
                    Thread.sleep(RETRY_DELAY_BASE_MS * attempts);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new SunbayNetworkException("Request interrupted", ie, false);
                }
            }
        }

        throw new SunbayNetworkException("Request failed after " + maxAttempts + " attempts", true);
    }
    
    /**
     * Convert getter method name to parameter name.
     * e.g. {@code getAppId} -> {@code appId}, {@code isPushToTerminal} -> {@code pushToTerminal}.
     *
     * @param methodName method name
     * @return parameter name
     */
    private String convertMethodNameToParamName(String methodName) {
        String rest;
        if (methodName.startsWith(GETTER_PREFIX) && methodName.length() > GETTER_PREFIX.length()) {
            rest = methodName.substring(GETTER_PREFIX.length());
        } else if (methodName.startsWith(BOOLEAN_GETTER_PREFIX)
                && methodName.length() > BOOLEAN_GETTER_PREFIX.length()) {
            rest = methodName.substring(BOOLEAN_GETTER_PREFIX.length());
        } else {
            return methodName;
        }
        return Character.toLowerCase(rest.charAt(0)) + rest.substring(1);
    }

    /**
     * Add common headers to request
     *
     * @param request HTTP request
     * @param method  HTTP method
     */
    private void addCommonHeaders(HttpUriRequestBase request, String method) {
        request.addHeader(HEADER_AUTHORIZATION, ApiConstants.AUTHORIZATION_BEARER_PREFIX + apiKey);
        request.addHeader(HEADER_REQUEST_ID, IdGenerator.generateRequestId());
        request.addHeader(HEADER_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        request.addHeader(HEADER_USER_AGENT, UserAgentUtil.getUserAgent());

        if (ApiConstants.HTTP_METHOD_POST.equalsIgnoreCase(method)) {
            // Content-Type is also carried by the StringEntity for POST, but adding the
            // header explicitly keeps request-logging output symmetric with 4.x.
            request.addHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON);
        }
    }


    /**
     * Execute HTTP request
     *
     * @param request      HTTP request
     * @param responseType response type class
     * @param <T>          response type
     * @return response object
     */
    private <T extends BaseResponse> T doExecute(HttpUriRequestBase request, String requestBody, Class<T> responseType) {
        String requestUrl = safeGetUri(request);
        String requestMethod = request.getMethod();

        // Log request
        if (log.isInfoEnabled()) {
            String headers = formatHeadersForLogging(request);
            if (requestBody != null && !requestBody.isEmpty()) {
                log.info("Request {} {} - Headers: {} - Body: {}", requestMethod, requestUrl, headers, requestBody);
            } else {
                log.info("Request {} {} - Headers: {}", requestMethod, requestUrl, headers);
            }
        }

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getCode();
            HttpEntity entity = response.getEntity();
            String responseBody = entity != null ? EntityUtils.toString(entity, StandardCharsets.UTF_8) : null;

            // Log response
            if (log.isInfoEnabled()) {
                log.info("Response {} {} - Status: {}, Body: {}", requestMethod, requestUrl, statusCode, responseBody);
            }

            if (statusCode >= ApiConstants.HTTP_STATUS_OK_START && statusCode < ApiConstants.HTTP_STATUS_OK_END) {
                if (responseBody == null || responseBody.trim().isEmpty()) {
                    throw new SunbayNetworkException("Empty response body", false);
                }
                
                // Parse response with data field support
                // parseResponse will throw SunbayBusinessException if business code is not success
                return parseResponse(responseBody, responseType);
            } else {
                String errorMessage = buildErrorMessage(statusCode);
                boolean retryable = shouldSignalRetryable(requestMethod, isRetryableHttpStatus(statusCode));
                if (log.isDebugEnabled()) {
                    log.debug("HTTP error {} {} - Status: {}, Message: {}, retryable: {}",
                            requestMethod, requestUrl, statusCode, errorMessage, retryable);
                }
                throw new SunbayNetworkException(errorMessage, retryable);
            }
        } catch (SocketTimeoutException e) {
            boolean retryable = shouldSignalRetryable(requestMethod, true);
            if (log.isDebugEnabled()) {
                log.debug("Request timeout {} {}: {} (retryable={})",
                        requestMethod, requestUrl, e.getMessage(), retryable);
            }
            throw new SunbayNetworkException("Request timeout", e, retryable);
        } catch (IOException e) {
            SunbayNetworkException translated = translateIoException(requestMethod, e);
            if (log.isDebugEnabled()) {
                log.debug("Network error {} {}: {}",
                        requestMethod, requestUrl, e.toString());
            }
            throw translated;
        } catch (org.apache.hc.core5.http.ParseException e) {
            // Server returned a body that could not be parsed as an HTTP entity
            // (malformed headers / body). Treat as a hard, non-retryable error.
            if (log.isDebugEnabled()) {
                log.debug("Malformed HTTP response {} {}: {}",
                        requestMethod, requestUrl, e.toString());
            }
            throw new SunbayNetworkException("Malformed HTTP response: " + e.getMessage(), e, false);
        }
    }

    /**
     * Get the request URI as a string for logging, tolerating the checked
     * {@link URISyntaxException} thrown by
     * {@link HttpUriRequestBase#getUri()} in HttpClient 5.x. Because we build
     * every request from a validated {@link URI}, this call is not expected to
     * fail in practice; the fallback is defensive.
     */
    private static String safeGetUri(HttpUriRequestBase request) {
        try {
            return request.getUri().toString();
        } catch (URISyntaxException e) {
            return request.getMethod() + " " + request.getRequestUri();
        }
    }
    
    /**
     * Translate a low-level {@link IOException} into a {@link SunbayNetworkException} whose
     * {@code retryable} flag reflects whether an immediate retry has any chance of success.
     * <p>
     * Errors that indicate a stable misconfiguration (bad DNS, refused connection, bad TLS
     * setup) are marked non-retryable so upper layers do not waste time and back-off budget
     * on them. Transient IO (connection reset, unexpected EOF, ...) is potentially retryable
     * for idempotent methods only; see {@link #shouldSignalRetryable(String, boolean)}.
     */
    private SunbayNetworkException translateIoException(String requestMethod, IOException e) {
        if (e instanceof UnknownHostException) {
            return new SunbayNetworkException("DNS resolution failed: " + e.getMessage(), e, false);
        }
        if (e instanceof SSLHandshakeException) {
            return new SunbayNetworkException("TLS handshake failed: " + e.getMessage(), e, false);
        }
        if (e instanceof ConnectException) {
            return new SunbayNetworkException("Connection refused: " + e.getMessage(), e, false);
        }
        boolean retryable = shouldSignalRetryable(requestMethod, true);
        return new SunbayNetworkException("Network error: " + e.getMessage(), e, retryable);
    }

    /**
     * Whether the given HTTP status code represents a transient condition that is
     * worth retrying. 4xx client errors are assumed to be caller mistakes (bad
     * request, unauthorized, not found, ...) and are not retried. Only 408, 429
     * and the transient 5xx codes surface as retryable.
     */
    private boolean isRetryableHttpStatus(int statusCode) {
        return statusCode == HTTP_STATUS_REQUEST_TIMEOUT
                || statusCode == HTTP_STATUS_TOO_MANY_REQUESTS
                || statusCode == HTTP_STATUS_BAD_GATEWAY
                || statusCode == HTTP_STATUS_SERVICE_UNAVAILABLE
                || statusCode == HTTP_STATUS_GATEWAY_TIMEOUT;
    }

    /**
     * Central policy for the {@code retryable} flag exposed on {@link SunbayNetworkException}.
     * <p>
     * A failure is only signaled as retryable when both:
     * <ul>
     *   <li>the underlying failure is naturally transient (timeout, 5xx, connection
     *       reset, ...), i.e. {@code naturallyRetryable} is {@code true}, and</li>
     *   <li>the HTTP method is safe to retry blindly. Only GET qualifies. For POST the
     *       SDK cannot know whether the server has already processed the request, so
     *       {@code retryable} is forced to {@code false} to prevent callers from causing
     *       double-charges by naively looping on {@link SunbayNetworkException#isRetryable()}.</li>
     * </ul>
     */
    private boolean shouldSignalRetryable(String requestMethod, boolean naturallyRetryable) {
        return naturallyRetryable
                && ApiConstants.HTTP_METHOD_GET.equalsIgnoreCase(requestMethod);
    }

    /**
     * Format request headers for logging with sensitive information masked
     *
     * @param request HTTP request
     * @return formatted headers string
     */
    private String formatHeadersForLogging(HttpUriRequestBase request) {
        StringBuilder sb = new StringBuilder("{");
        Header[] headers = request.getHeaders();
        boolean first = true;
        
        for (Header header : headers) {
            if (!first) {
                sb.append(", ");
            }
            first = false;
            
            String name = header.getName();
            String value = header.getValue();
            
            // Mask sensitive headers
            if (HEADER_AUTHORIZATION.equalsIgnoreCase(name)) {
                value = maskAuthorizationHeader(value);
            }
            
            sb.append("\"").append(name).append("\":\"").append(value).append("\"");
        }
        
        sb.append("}");
        return sb.toString();
    }

    /**
     * Mask authorization header value to hide sensitive information
     *
     * @param authValue original authorization header value
     * @return masked authorization header value
     */
    private String maskAuthorizationHeader(String authValue) {
        if (authValue == null || authValue.isEmpty()) {
            return "";
        }
        
        // If it starts with "Bearer ", mask the token part
        if (authValue.startsWith(ApiConstants.AUTHORIZATION_BEARER_PREFIX)) {
            String token = authValue.substring(ApiConstants.AUTHORIZATION_BEARER_PREFIX.length());
            if (token.length() > 8) {
                // Show first 4 and last 4 characters, mask the middle
                return ApiConstants.AUTHORIZATION_BEARER_PREFIX + 
                       token.substring(0, 4) + "****" + token.substring(token.length() - 4);
            } else {
                // If token is too short, just show "****"
                return ApiConstants.AUTHORIZATION_BEARER_PREFIX + "****";
            }
        }
        
        // For other formats, mask completely
        return "****";
    }

    /**
     * Parse a response body according to the Sunbay API envelope contract.
     * <p>
     * Contract enforced here:
     * </p>
     * <pre>
     *   {
     *     "code":    "0" for success, other codes for business errors,
     *     "msg":     human-readable message,
     *     "data":    a JSON object for success (may be null when the operation
     *                has no return payload, e.g. void / abort),
     *     "traceId": server-side trace id
     *   }
     * </pre>
     * <p>
     * The following outcomes are produced:
     * </p>
     * <ul>
     *   <li>Success ({@code code == "0"}) with an object {@code data} - bind
     *       {@code data} into {@code T}, then attach {@code code}/{@code msg}/{@code traceId}.</li>
     *   <li>Success with {@code data == null} or missing - return a {@code T} with all
     *       payload fields left at their defaults, with the envelope fields attached.</li>
     *   <li>Business error ({@code code != "0"}) - throw {@link SunbayBusinessException}
     *       carrying the server-supplied {@code code}, {@code msg}, {@code traceId}.</li>
     *   <li>Contract violation (body not JSON, root not an object, {@code data} not
     *       an object) - throw {@link SunbayBusinessException} with a client-side
     *       parse-error code ({@link ApiConstants#ERROR_CODE_PARAMETER_ERROR}).</li>
     * </ul>
     *
     * @param responseBody response body JSON string
     * @param responseType response type class
     * @param <T>          response type
     * @return parsed response object, never {@code null} on successful return
     */
    private <T extends BaseResponse> T parseResponse(String responseBody, Class<T> responseType) {
        JsonNode rootNode = parseRootNode(responseBody);

        // Use hasNonNull to avoid treating a JSON null value as the string "null".
        String code = rootNode.hasNonNull(ApiConstants.JSON_FIELD_CODE)
                ? rootNode.get(ApiConstants.JSON_FIELD_CODE).asText() : null;
        String msg = rootNode.hasNonNull(ApiConstants.JSON_FIELD_MSG)
                ? rootNode.get(ApiConstants.JSON_FIELD_MSG).asText() : null;
        String traceId = rootNode.hasNonNull(ApiConstants.JSON_FIELD_TRACE_ID)
                ? rootNode.get(ApiConstants.JSON_FIELD_TRACE_ID).asText() : null;
        JsonNode dataNode = rootNode.get(ApiConstants.JSON_FIELD_DATA);

        // Business error short-circuit: no point trying to bind the data payload.
        if (!ApiConstants.RESPONSE_SUCCESS_CODE.equals(code)) {
            if (log.isDebugEnabled()) {
                log.debug("API business error - code: {}, msg: {}, traceId: {}", code, msg, traceId);
            }
            throw new SunbayBusinessException(code, msg, traceId);
        }

        T result = bindDataNode(dataNode, responseType, traceId);
        result.setCode(code);
        result.setMsg(msg);
        result.setTraceId(traceId);
        return result;
    }

    /**
     * Parse the response body into a Jackson tree and enforce that the root is a
     * JSON object. Any deviation from the envelope contract fails loudly here.
     */
    private JsonNode parseRootNode(String responseBody) {
        JsonNode rootNode;
        try {
            rootNode = JsonUtil.readTree(responseBody);
        } catch (JsonProcessingException e) {
            throw new SunbayBusinessException(
                    ApiConstants.ERROR_CODE_PARAMETER_ERROR,
                    "Failed to parse response body as JSON",
                    null,
                    e);
        }
        if (rootNode == null || !rootNode.isObject()) {
            throw new SunbayBusinessException(
                    ApiConstants.ERROR_CODE_PARAMETER_ERROR,
                    "Response body is not a JSON object (contract violation)",
                    null);
        }
        return rootNode;
    }

    /**
     * Bind the {@code data} node into a {@code T}. Legal shapes are:
     * <ul>
     *   <li>Missing or JSON null - a fresh empty {@code T} is produced.</li>
     *   <li>JSON object - {@code data} is deserialized into {@code T}.</li>
     * </ul>
     * Anything else (array, primitive) is a contract violation and throws.
     */
    private <T extends BaseResponse> T bindDataNode(JsonNode dataNode, Class<T> responseType, String traceId) {
        try {
            if (dataNode == null || dataNode.isNull()) {
                // No payload (e.g. void / abort); still return a valid T so callers
                // can safely read envelope fields without a null check on the result.
                return JsonUtil.emptyValue(responseType);
            }
            if (dataNode.isObject()) {
                return JsonUtil.treeToValue(dataNode, responseType);
            }
            throw new SunbayBusinessException(
                    ApiConstants.ERROR_CODE_PARAMETER_ERROR,
                    "Response 'data' must be a JSON object or null, but was "
                            + dataNode.getNodeType() + " (contract violation)",
                    traceId);
        } catch (JsonProcessingException e) {
            throw new SunbayBusinessException(
                    ApiConstants.ERROR_CODE_PARAMETER_ERROR,
                    "Failed to bind response 'data' to " + responseType.getSimpleName(),
                    traceId,
                    e);
        }
    }

    /**
     * Build a short error message from the HTTP status code. Only the status
     * code and its high-level category are included; the response body has
     * already been logged at INFO level in {@link #doExecute}, so re-including
     * it here would just double the noise and, for large error pages, bloat
     * downstream exception trackers.
     *
     * @param statusCode HTTP status code
     * @return short error message suitable for {@link SunbayNetworkException}
     */
    private String buildErrorMessage(int statusCode) {
        String category;
        if (statusCode >= ApiConstants.HTTP_STATUS_CLIENT_ERROR_START
                && statusCode < ApiConstants.HTTP_STATUS_CLIENT_ERROR_END) {
            category = " (Client Error)";
        } else if (statusCode >= ApiConstants.HTTP_STATUS_SERVER_ERROR_START) {
            category = " (Server Error)";
        } else {
            category = "";
        }
        return "HTTP " + statusCode + category;
    }

    /**
     * Close the HTTP client. This also releases the underlying connection manager
     * and stops the eviction background thread, since {@link CloseableHttpClient#close()}
     * takes care of shutting the pool it owns.
     */
    @Override
    public void close() {
        if (httpClient == null) {
            return;
        }
        try {
            httpClient.close();
        } catch (IOException e) {
            // Not a hard failure - the JVM will reclaim the resources at exit either way.
            // Log at DEBUG so diagnostics are available without spamming production logs.
            if (log.isDebugEnabled()) {
                log.debug("Error while closing HttpClient", e);
            }
        }
    }
}
