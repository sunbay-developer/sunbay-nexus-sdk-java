package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;

/**
 * Refund response
 *
 * @since 2025-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RefundResponse extends BaseResponse {

    /**
     * SUNBAY Nexus transaction ID for this refund transaction, used for subsequent queries and notifications
     */
    private String transactionId;

    /**
     * Reference order ID (same as original transaction for refund with reference, new refund reference order ID for refund without reference)
     */
    private String referenceOrderId;

    /**
     * Transaction request ID for this refund, returned as-is from request
     */
    private String transactionRequestId;

    /**
     * Original transaction ID (only returned for refund with reference)
     */
    private String originalTransactionId;

    /**
     * Original transaction request ID (only returned for refund with reference)
     */
    private String originalTransactionRequestId;
}
