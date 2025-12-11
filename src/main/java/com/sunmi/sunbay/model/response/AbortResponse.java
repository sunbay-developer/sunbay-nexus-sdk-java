package com.sunmi.sunbay.model.response;

import com.sunmi.sunbay.model.common.BaseResponse;

/**
 * Abort response
 *
 * @since 2025-12-10
 */
public class AbortResponse extends BaseResponse {

    private String originalTransactionId;
    private String originalTransactionRequestId;

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
