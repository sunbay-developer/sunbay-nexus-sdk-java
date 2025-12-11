package com.sunmi.sunbay.exception;

/**
 * Sunbay network exception
 *
 * @since 2025-12-10
 */
public class SunbayNetworkException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final boolean retryable;

    public SunbayNetworkException(String message, Throwable cause, boolean retryable) {
        super(message, cause);
        this.retryable = retryable;
    }

    public SunbayNetworkException(String message, boolean retryable) {
        super(message);
        this.retryable = retryable;
    }

    public boolean isRetryable() {
        return retryable;
    }

    @Override
    public String toString() {
        return "SunbayNetworkException{" +
                "message='" + getMessage() + '\'' +
                ", retryable=" + retryable +
                '}';
    }
}
