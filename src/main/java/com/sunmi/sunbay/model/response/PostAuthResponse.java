package com.sunmi.sunbay.model.response;

import com.sunmi.sunbay.model.common.BaseResponse;

/**
 * Post authorization response
 *
 * @since 2025-12-10
 */
public class PostAuthResponse extends BaseResponse {

    private String transactionId;
    private String transactionRequestId;
    private String originalTransactionId;
    private String originalTransactionRequestId;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionRequestId() {
        return transactionRequestId;
    }

    public void setTransactionRequestId(String transactionRequestId) {
        this.transactionRequestId = transactionRequestId;
    }

    public String getOriginalTransactionId() {
        return originalTransactionId;
    }

    public void setOriginalTransactionId(String originalTransactionId) {
        this.originalTransactionId = originalTransactionId;
    }

    public String getOriginalTransactionRequestId() {
        return originalTransactionRequestId;
    }

    public void setOriginalTransactionRequestId(String originalTransactionRequestId) {
        this.originalTransactionRequestId = originalTransactionRequestId;
    }
}
