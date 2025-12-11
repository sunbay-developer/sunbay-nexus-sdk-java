package com.sunmi.sunbay.exception;

/**
 * Sunbay SDK business exception
 * <p>
 * Used for API business exceptions and parameter validation errors
 * </p>
 *
 * @since 2025-12-10
 */
public class SunbayBusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * API error code (for API exceptions)
     */
    private String code;

    /**
     * Trace ID (for API exceptions)
     */
    private String traceId;

    public SunbayBusinessException(String message) {
        super(message);
    }

    public SunbayBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public SunbayBusinessException(Throwable cause) {
        super(cause);
    }

    /**
     * Create API exception with code and traceId
     *
     * @param code    API error code
     * @param message error message
     * @param traceId trace ID
     */
    public SunbayBusinessException(String code, String message, String traceId) {
        super(message);
        this.code = code;
        this.traceId = traceId;
    }

    public String getCode() {
        return code;
    }

    public String getTraceId() {
        return traceId;
    }

    @Override
    public String toString() {
        if (code != null) {
            return "SunbayBusinessException{" +
                    "code='" + code + '\'' +
                    ", message='" + getMessage() + '\'' +
                    ", traceId='" + traceId + '\'' +
                    '}';
        }
        return "SunbayBusinessException{" +
                "message='" + getMessage() + '\'' +
                '}';
    }
}

