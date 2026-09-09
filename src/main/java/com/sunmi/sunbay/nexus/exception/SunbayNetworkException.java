package com.sunmi.sunbay.nexus.exception;

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

    /**
     * Internal SDK signal, not intended for callers to base retry decisions on.
     * <p>
     * The SDK already performs its own retry policy for idempotent requests
     * (GET) inside the HTTP layer. For non-idempotent requests (POST such as
     * {@code sale}, {@code refund}, {@code void}, {@code batchClose}), the SDK
     * cannot know whether the server has already processed the request, so
     * this flag will always be {@code false} regardless of the underlying
     * cause. Callers that need to retry a POST must do so explicitly at the
     * business layer, using the API's own idempotency key (for example
     * {@code transactionRequestId}) to guarantee safety.
     * </p>
     *
     * @return legacy internal retry hint; do not use for new code
     * @deprecated Callers should own their retry policy. The value of this
     *             flag will always be {@code false} for POST failures and is
     *             therefore not useful for making retry decisions. This method
     *             is kept only for backward compatibility and may be removed
     *             in a future major release.
     */
    @Deprecated
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
