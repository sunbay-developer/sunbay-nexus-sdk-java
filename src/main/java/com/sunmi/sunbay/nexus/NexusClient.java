package com.sunmi.sunbay.nexus;

import com.sunmi.sunbay.nexus.client.HttpClient;
import com.sunmi.sunbay.nexus.constant.ApiConstants;
import com.sunmi.sunbay.nexus.exception.SunbayBusinessException;
import com.sunmi.sunbay.nexus.model.request.*;
import com.sunmi.sunbay.nexus.model.response.*;

/**
 * Sunbay SDK main client
 * <p>
 * This client is thread-safe and can be safely used by multiple threads.
 * The client implements AutoCloseable, so it can be used with try-with-resources.
 * </p>
 *
 * @since 2025-12-10
 */
public class NexusClient implements AutoCloseable {

    private static final String DEFAULT_BASE_URL = "https://open.sunbay.us";
    private static final int DEFAULT_CONNECT_TIMEOUT = 30000;
    private static final int DEFAULT_READ_TIMEOUT = 60000;
    private static final int DEFAULT_MAX_RETRIES = 3;
    private static final int DEFAULT_MAX_TOTAL = 200;
    private static final int DEFAULT_MAX_PER_ROUTE = 20;

    private final HttpClient httpClient;

    private NexusClient(Builder builder) {
        this.httpClient = new HttpClient(
                builder.apiKey,
                builder.baseUrl,
                builder.connectTimeout,
                builder.readTimeout,
                builder.maxRetries,
                builder.maxTotal,
                builder.maxPerRoute
        );
    }

    /**
     * Sale transaction
     *
     * @param request sale request
     * @return sale response
     */
    public SaleResponse sale(SaleRequest request) {
        if (request == null) {
            throw new SunbayBusinessException(ApiConstants.ERROR_CODE_PARAMETER_ERROR, "SaleRequest cannot be null", null);
        }
        return httpClient.post(ApiConstants.PATH_SALE, request, SaleResponse.class);
    }

    /**
     * Authorization (pre-auth)
     *
     * @param request auth request
     * @return auth response
     */
    public AuthResponse auth(AuthRequest request) {
        if (request == null) {
            throw new SunbayBusinessException(ApiConstants.ERROR_CODE_PARAMETER_ERROR, "AuthRequest cannot be null", null);
        }
        return httpClient.post(ApiConstants.PATH_AUTH, request, AuthResponse.class);
    }

    /**
     * Forced authorization
     *
     * @param request forced auth request
     * @return forced auth response
     */
    public ForcedAuthResponse forcedAuth(ForcedAuthRequest request) {
        if (request == null) {
            throw new SunbayBusinessException(ApiConstants.ERROR_CODE_PARAMETER_ERROR, "ForcedAuthRequest cannot be null", null);
        }
        return httpClient.post(ApiConstants.PATH_FORCED_AUTH, request, ForcedAuthResponse.class);
    }

    /**
     * Incremental authorization
     *
     * @param request incremental auth request
     * @return incremental auth response
     */
    public IncrementalAuthResponse incrementalAuth(IncrementalAuthRequest request) {
        if (request == null) {
            throw new SunbayBusinessException(ApiConstants.ERROR_CODE_PARAMETER_ERROR, "IncrementalAuthRequest cannot be null", null);
        }
        return httpClient.post(ApiConstants.PATH_INCREMENTAL_AUTH, request, IncrementalAuthResponse.class);
    }

    /**
     * Post authorization (pre-auth completion)
     *
     * @param request post auth request
     * @return post auth response
     */
    public PostAuthResponse postAuth(PostAuthRequest request) {
        if (request == null) {
            throw new SunbayBusinessException(ApiConstants.ERROR_CODE_PARAMETER_ERROR, "PostAuthRequest cannot be null", null);
        }
        return httpClient.post(ApiConstants.PATH_POST_AUTH, request, PostAuthResponse.class);
    }

    /**
     * Refund
     *
     * @param request refund request
     * @return refund response
     */
    public RefundResponse refund(RefundRequest request) {
        if (request == null) {
            throw new SunbayBusinessException(ApiConstants.ERROR_CODE_PARAMETER_ERROR, "RefundRequest cannot be null", null);
        }
        return httpClient.post(ApiConstants.PATH_REFUND, request, RefundResponse.class);
    }

    /**
     * Void transaction
     *
     * @param request void request
     * @return void response
     */
    public VoidResponse voidTransaction(VoidRequest request) {
        if (request == null) {
            throw new SunbayBusinessException(ApiConstants.ERROR_CODE_PARAMETER_ERROR, "VoidRequest cannot be null", null);
        }
        return httpClient.post(ApiConstants.PATH_VOID, request, VoidResponse.class);
    }

    /**
     * Abort transaction
     *
     * @param request abort request
     * @return abort response
     */
    public AbortResponse abort(AbortRequest request) {
        if (request == null) {
            throw new SunbayBusinessException(ApiConstants.ERROR_CODE_PARAMETER_ERROR, "AbortRequest cannot be null", null);
        }
        return httpClient.post(ApiConstants.PATH_ABORT, request, AbortResponse.class);
    }

    /**
     * Tip adjust
     *
     * @param request tip adjust request
     * @return tip adjust response
     */
    public TipAdjustResponse tipAdjust(TipAdjustRequest request) {
        if (request == null) {
            throw new SunbayBusinessException(ApiConstants.ERROR_CODE_PARAMETER_ERROR, "TipAdjustRequest cannot be null", null);
        }
        return httpClient.post(ApiConstants.PATH_TIP_ADJUST, request, TipAdjustResponse.class);
    }

    /**
     * Query transaction
     *
     * @param request query request
     * @return query response
     */
    public QueryResponse query(QueryRequest request) {
        if (request == null) {
            throw new SunbayBusinessException(ApiConstants.ERROR_CODE_PARAMETER_ERROR, "QueryRequest cannot be null", null);
        }
        return httpClient.get(ApiConstants.PATH_QUERY, request, QueryResponse.class);
    }

    /**
     * Batch close
     *
     * @param request batch close request
     * @return batch close response
     */
    public BatchCloseResponse batchClose(BatchCloseRequest request) {
        if (request == null) {
            throw new SunbayBusinessException(ApiConstants.ERROR_CODE_PARAMETER_ERROR, "BatchCloseRequest cannot be null", null);
        }
        return httpClient.post(ApiConstants.PATH_BATCH_CLOSE, request, BatchCloseResponse.class);
    }

    /**
     * Close client and release resources
     */
    public void close() {
        httpClient.close();
    }

    /**
     * Builder for SunbayClient
     */
    public static class Builder {
        private String apiKey;
        private String baseUrl = DEFAULT_BASE_URL;
        private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        private int readTimeout = DEFAULT_READ_TIMEOUT;
        private int maxRetries = DEFAULT_MAX_RETRIES;
        private Integer maxTotal = DEFAULT_MAX_TOTAL;
        private Integer maxPerRoute = DEFAULT_MAX_PER_ROUTE;

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder connectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder readTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder maxRetries(int maxRetries) {
            this.maxRetries = maxRetries;
            return this;
        }

        /**
         * Set maximum total connections in the connection pool
         *
         * @param maxTotal maximum total connections (default: 200)
         * @return builder
         */
        public Builder maxTotal(int maxTotal) {
            this.maxTotal = maxTotal;
            return this;
        }

        /**
         * Set maximum connections per route in the connection pool
         *
         * @param maxPerRoute maximum connections per route (default: 20)
         * @return builder
         */
        public Builder maxPerRoute(int maxPerRoute) {
            this.maxPerRoute = maxPerRoute;
            return this;
        }

        public NexusClient build() {
            if (apiKey == null || apiKey.isEmpty()) {
                throw new SunbayBusinessException(ApiConstants.ERROR_CODE_PARAMETER_ERROR, "API key cannot be null or empty", null);
            }
            return new NexusClient(this);
        }
    }
}
