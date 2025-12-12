package com.sunmi.sunbay.nexus.model.response;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;

/**
 * Tip adjust response
 *
 * @since 2025-12-12
 */
public class TipAdjustResponse extends BaseResponse {

    private String originalTransactionId;
    private String originalTransactionRequestId;
    private Double tipAmount;

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

    public Double getTipAmount() {
        return tipAmount;
    }

    public void setTipAmount(Double tipAmount) {
        this.tipAmount = tipAmount;
    }
}
