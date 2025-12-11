package com.sunmi.sunbay.model.response;

import com.sunmi.sunbay.model.common.BaseResponse;

/**
 * Tip adjust response
 *
 * @since 2025-12-10
 */
public class TipAdjustResponse extends BaseResponse {

    private String transactionId;
    private String originalTransactionId;
    private String originalTransactionRequestId;
    private String transactionRequestId;
    private Double tipAmount;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public String getTransactionRequestId() {
        return transactionRequestId;
    }

    public void setTransactionRequestId(String transactionRequestId) {
        this.transactionRequestId = transactionRequestId;
    }

    public Double getTipAmount() {
        return tipAmount;
    }

    public void setTipAmount(Double tipAmount) {
        this.tipAmount = tipAmount;
    }
}
