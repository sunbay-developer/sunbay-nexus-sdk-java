package com.sunmi.sunbay.nexus.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunmi.sunbay.nexus.constant.ApiConstants;
import com.sunmi.sunbay.nexus.exception.SunbayBusinessException;
import com.sunmi.sunbay.nexus.exception.SunbayNetworkException;
import com.sunmi.sunbay.nexus.model.common.BaseResponse;
import com.sunmi.sunbay.nexus.util.IdGenerator;
import com.sunmi.sunbay.nexus.util.JsonUtil;
import com.sunmi.sunbay.nexus.util.UserAgentUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

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

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(readTimeout)
                .setConnectionRequestTimeout(connectTimeout)
                .build();

        // Create connection pool manager
        this.connectionManager = new PoolingHttpClientConnectionManager();
        if (maxTotal != null) {
            connectionManager.setMaxTotal(maxTotal);
        }
        if (maxPerRoute != null) {
            connectionManager.setDefaultMaxPerRoute(maxPerRoute);
        }

        this.httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
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
        httpPost.setEntity(new StringEntity(requestJson, StandardCharsets.UTF_8));

        addCommonHeaders(httpPost, ApiConstants.HTTP_METHOD_POST);

        return executeRequest(httpPost, responseType, false);
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
            URIBuilder uriBuilder = new URIBuilder(baseUrl + path);
            
            // Build query parameters from request object using reflection
            if (request != null) {
                Class<?> clazz = request.getClass();
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    String methodName = method.getName();
                    if (methodName.startsWith("get") && methodName.length() > ApiConstants.GETTER_METHOD_PREFIX_LENGTH 
                            && method.getParameterCount() == 0
                            && !methodName.equals("getClass")) {
                        try {
                            Object value = method.invoke(request);
                            if (value != null) {
                                String paramName = convertMethodNameToParamName(methodName);
                                uriBuilder.addParameter(paramName, String.valueOf(value));
                            }
                        } catch (Exception e) {
                            // Ignore reflection errors
                        }
                    }
                }
            }
            
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            addCommonHeaders(httpGet, ApiConstants.HTTP_METHOD_GET);

            return executeRequest(httpGet, responseType, true);
        } catch (URISyntaxException e) {
            throw new SunbayNetworkException("Invalid URL: " + e.getMessage(), e, false);
        }
    }
    
    /**
     * Convert getter method name to parameter name
     * e.g., getAppId -> appId, getTransactionId -> transactionId
     *
     * @param methodName method name
     * @return parameter name
     */
    private String convertMethodNameToParamName(String methodName) {
        if (methodName.startsWith("get") && methodName.length() > ApiConstants.GETTER_METHOD_PREFIX_LENGTH) {
            String rest = methodName.substring(3);
            return Character.toLowerCase(rest.charAt(0)) + rest.substring(1);
        }
        return methodName;
    }

    /**
     * Add common headers to request
     *
     * @param request HTTP request
     * @param method  HTTP method
     */
    private void addCommonHeaders(HttpRequestBase request, String method) {
        request.addHeader(HEADER_AUTHORIZATION, ApiConstants.AUTHORIZATION_BEARER_PREFIX + apiKey);
        request.addHeader(HEADER_REQUEST_ID, IdGenerator.generateRequestId());
        request.addHeader(HEADER_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        request.addHeader(HEADER_USER_AGENT, UserAgentUtil.getUserAgent());

        if (ApiConstants.HTTP_METHOD_POST.equalsIgnoreCase(method)) {
            request.addHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON);
        }
    }

    /**
     * Execute HTTP request with retry logic
     *
     * @param request      HTTP request
     * @param responseType response type class
     * @param retryable    whether the request is retryable
     * @param <T>          response type
     * @return response object
     */
    private <T extends BaseResponse> T executeRequest(HttpRequestBase request, Class<T> responseType, boolean retryable) {
        int attempts = 0;
        int maxAttempts = retryable ? maxRetries : 1;

        while (attempts < maxAttempts) {
            attempts++;
            try {
                return doExecute(request, responseType);
            } catch (SunbayNetworkException e) {
                if (!retryable || attempts >= maxAttempts) {
                    if (log.isWarnEnabled()) {
                        log.warn("Request failed after {} attempts: {}", attempts, e.getMessage());
                    }
                    throw e;
                }
                if (log.isDebugEnabled()) {
                    log.debug("Request failed, retrying ({}/{}) after delay: {}", attempts, maxAttempts, e.getMessage());
                }
                // Retry after delay
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
     * Execute HTTP request
     *
     * @param request      HTTP request
     * @param responseType response type class
     * @param <T>          response type
     * @return response object
     */
    private <T extends BaseResponse> T doExecute(HttpRequestBase request, Class<T> responseType) {
        String requestUrl = request.getURI().toString();
        String requestMethod = request.getMethod();
        String requestBody = extractRequestBodyBeforeExecute(request);
        
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
            int statusCode = response.getStatusLine().getStatusCode();
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
                T result = parseResponse(responseBody, responseType);
                if (result == null) {
                    throw new SunbayNetworkException("Failed to parse response body", false);
                }
                if (!result.isSuccess()) {
                    if (log.isErrorEnabled()) {
                        log.error("API error {} {} - code: {}, msg: {}, traceId: {}", 
                                requestMethod, requestUrl, result.getCode(), result.getMsg(), result.getTraceId());
                    }
                    throw new SunbayBusinessException(result.getCode(), result.getMsg(), result.getTraceId());
                }
                return result;
            } else {
                String errorMessage = buildErrorMessage(statusCode, responseBody);
                if (log.isErrorEnabled()) {
                    log.error("HTTP error {} {} - Status: {}, Message: {}", 
                            requestMethod, requestUrl, statusCode, errorMessage);
                }
                throw new SunbayNetworkException(errorMessage, false);
            }
        } catch (SocketTimeoutException e) {
            if (log.isWarnEnabled()) {
                log.warn("Request timeout {} {}: {}", requestMethod, requestUrl, e.getMessage());
            }
            throw new SunbayNetworkException("Request timeout", e, true);
        } catch (IOException e) {
            if (log.isWarnEnabled()) {
                log.warn("Network error {} {}: {}", requestMethod, requestUrl, e.getMessage());
            }
            throw new SunbayNetworkException("Network error: " + e.getMessage(), e, true);
        }
    }
    
    /**
     * Format request headers for logging with sensitive information masked
     *
     * @param request HTTP request
     * @return formatted headers string
     */
    private String formatHeadersForLogging(HttpRequestBase request) {
        StringBuilder sb = new StringBuilder("{");
        Header[] headers = request.getAllHeaders();
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
     * Extract request body from HTTP request before execution
     * Note: This method reads the entity, so we need to recreate it after reading
     *
     * @param request HTTP request
     * @return request body string, or null if not available
     */
    private String extractRequestBodyBeforeExecute(HttpRequestBase request) {
        if (request instanceof HttpPost) {
            HttpPost httpPost = (HttpPost) request;
            HttpEntity entity = httpPost.getEntity();
            if (entity instanceof StringEntity) {
                try {
                    String body = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                    // Recreate entity since it was consumed
                    httpPost.setEntity(new StringEntity(body, StandardCharsets.UTF_8));
                    return body;
                } catch (IOException e) {
                    // Ignore
                }
            }
        }
        return null;
    }

    /**
     * Parse response with data field support
     * API returns: {"code":"0","msg":"Success","data":{...},"traceId":"..."}
     * Need to extract data field and merge with base response
     *
     * @param responseBody response body JSON string
     * @param responseType response type class
     * @param <T> response type
     * @return parsed response object
     */
    private <T extends BaseResponse> T parseResponse(String responseBody, Class<T> responseType) {
        try {
            JsonNode rootNode = JsonUtil.getObjectMapper().readTree(responseBody);
            
            // Extract base fields (code, msg, traceId)
            String code = rootNode.has(ApiConstants.JSON_FIELD_CODE) ? rootNode.get(ApiConstants.JSON_FIELD_CODE).asText() : null;
            String msg = rootNode.has(ApiConstants.JSON_FIELD_MSG) ? rootNode.get(ApiConstants.JSON_FIELD_MSG).asText() : null;
            String traceId = rootNode.has(ApiConstants.JSON_FIELD_TRACE_ID) ? rootNode.get(ApiConstants.JSON_FIELD_TRACE_ID).asText() : null;
            
            // Extract data field if exists
            JsonNode dataNode = rootNode.has(ApiConstants.JSON_FIELD_DATA) ? rootNode.get(ApiConstants.JSON_FIELD_DATA) : null;
            
            // Create response object
            T result;
            if (dataNode != null && !dataNode.isNull()) {
                // Merge data field with base response
                // First, parse the data node to response type
                result = JsonUtil.getObjectMapper().treeToValue(dataNode, responseType);
            } else {
                // No data field, parse entire response
                result = JsonUtil.fromJson(responseBody, responseType);
            }
            
            // Set base fields
            if (result != null) {
                result.setCode(code);
                result.setMsg(msg);
                result.setTraceId(traceId);
            }
            
            return result;
        } catch (Exception e) {
            // Fallback to direct parsing
            if (log.isDebugEnabled()) {
                log.debug("Failed to parse response with data field, fallback to direct parsing: {}", e.getMessage());
            }
            return JsonUtil.fromJson(responseBody, responseType);
        }
    }

    /**
     * Build error message from HTTP status code and response body
     *
     * @param statusCode HTTP status code
     * @param responseBody response body
     * @return error message
     */
    private String buildErrorMessage(int statusCode, String responseBody) {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP ").append(statusCode);
        
        if (statusCode >= ApiConstants.HTTP_STATUS_CLIENT_ERROR_START && statusCode < ApiConstants.HTTP_STATUS_CLIENT_ERROR_END) {
            sb.append(" (Client Error)");
        } else if (statusCode >= ApiConstants.HTTP_STATUS_SERVER_ERROR_START) {
            sb.append(" (Server Error)");
        }
        
        if (responseBody != null && !responseBody.trim().isEmpty()) {
            sb.append(" - ").append(responseBody);
        }
        
        return sb.toString();
    }

    /**
     * Close HTTP client and connection manager
     */
    @Override
    public void close() {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            // Ignore
        } finally {
            if (connectionManager != null) {
                connectionManager.close();
            }
        }
    }
}
