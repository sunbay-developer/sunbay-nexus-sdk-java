package com.sunmi.sunbay.nexus.constant;

/**
 * API constants
 *
 * @since 2025-12-10
 */
public class ApiConstants {

    /**
     * Parameter error code (C17)
     */
    public static final String ERROR_CODE_PARAMETER_ERROR = "C17";

    /**
     * HTTP methods
     */
    public static final String HTTP_METHOD_POST = "POST";
    public static final String HTTP_METHOD_GET = "GET";

    /**
     * HTTP status codes
     */
    public static final int HTTP_STATUS_OK_START = 200;
    public static final int HTTP_STATUS_OK_END = 300;
    public static final int HTTP_STATUS_CLIENT_ERROR_START = 400;
    public static final int HTTP_STATUS_CLIENT_ERROR_END = 500;
    public static final int HTTP_STATUS_SERVER_ERROR_START = 500;

    /**
     * Response success code
     */
    public static final String RESPONSE_SUCCESS_CODE = "0";

    /**
     * Authorization header prefix
     */
    public static final String AUTHORIZATION_BEARER_PREFIX = "Bearer ";

    /**
     * JSON field names
     */
    public static final String JSON_FIELD_CODE = "code";
    public static final String JSON_FIELD_MSG = "msg";
    public static final String JSON_FIELD_DATA = "data";
    public static final String JSON_FIELD_TRACE_ID = "traceId";

    /**
     * Getter method name prefix length
     */
    public static final int GETTER_METHOD_PREFIX_LENGTH = 3;

    private ApiConstants() {
    }

    /**
     * Semi-integration API path prefix
     */
    public static final String SEMI_INTEGRATION_PREFIX = "/v1/semi-integration";

    /**
     * Semi-integration transaction API paths
     */
    public static final String PATH_SALE = SEMI_INTEGRATION_PREFIX + "/transaction/sale";
    public static final String PATH_AUTH = SEMI_INTEGRATION_PREFIX + "/transaction/auth";
    public static final String PATH_FORCED_AUTH = SEMI_INTEGRATION_PREFIX + "/transaction/forced-auth";
    public static final String PATH_INCREMENTAL_AUTH = SEMI_INTEGRATION_PREFIX + "/transaction/incremental-auth";
    public static final String PATH_POST_AUTH = SEMI_INTEGRATION_PREFIX + "/transaction/post-auth";
    public static final String PATH_REFUND = SEMI_INTEGRATION_PREFIX + "/transaction/refund";
    public static final String PATH_VOID = SEMI_INTEGRATION_PREFIX + "/transaction/void";
    public static final String PATH_ABORT = SEMI_INTEGRATION_PREFIX + "/transaction/abort";
    public static final String PATH_TIP_ADJUST = SEMI_INTEGRATION_PREFIX + "/transaction/tip-adjust";
    public static final String PATH_QUERY = SEMI_INTEGRATION_PREFIX + "/transaction/query";

    /**
     * Semi-integration settlement API paths
     */
    public static final String PATH_BATCH_CLOSE = SEMI_INTEGRATION_PREFIX + "/settlement/batch-close";
}
