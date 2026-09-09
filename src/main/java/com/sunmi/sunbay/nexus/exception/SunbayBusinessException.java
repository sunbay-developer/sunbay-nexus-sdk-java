package com.sunmi.sunbay.nexus.exception;

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

    /**
     * Create API exception with code, traceId and an underlying cause. Use this
     * when the exception is raised in a fallback path where the original failure
     * (JSON parse error, unexpected response shape, ...) should be preserved for
     * troubleshooting.
     *
     * @param code    API error code
     * @param message error message
     * @param traceId trace ID
     * @param cause   underlying exception that triggered this business exception
     * @since 2026-09-09
     */
    public SunbayBusinessException(String code, String message, String traceId, Throwable cause) {
        super(message, cause);
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

