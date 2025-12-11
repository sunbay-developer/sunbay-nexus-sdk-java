package com.sunmi.sunbay.model.response;

import com.sunmi.sunbay.model.common.BaseResponse;

/**
 * Authorization response
 *
 * @since 2025-12-10
 */
public class AuthResponse extends BaseResponse {

    private String transactionId;
    private String transactionRequestId;
    private String referenceOrderId;

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

    public String getReferenceOrderId() {
        return referenceOrderId;
    }

    public void setReferenceOrderId(String referenceOrderId) {
        this.referenceOrderId = referenceOrderId;
    }
}
