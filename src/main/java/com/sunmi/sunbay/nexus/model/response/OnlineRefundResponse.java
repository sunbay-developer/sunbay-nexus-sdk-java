package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;
import com.sunmi.sunbay.nexus.model.common.OnlineRefundAmount;

/**
 * Online refund response ({@code POST /v1/checkout/refund}).
 *
 * @since 2026-06-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OnlineRefundResponse extends BaseResponse {

    /**
     * SUNBAY Nexus transaction ID for this refund transaction
     */
    private String transactionId;

    /**
     * Transaction request ID, returned as-is from request
     */
    private String transactionRequestId;

    /**
     * Original transaction ID
     */
    private String originalTransactionId;

    /**
     * Transaction status: INITIAL(I)/PROCESSING(P)/SUCCESS(S)/FAIL(F)/CLOSED(C)
     */
    private String transactionStatus;

    /**
     * Transaction type, fixed as REFUND
     */
    private String transactionType;

    /**
     * Refund amount information (smallest currency unit)
     */
    private OnlineRefundAmount amount;

    /**
     * Refund creation time, ISO 8601 format
     */
    private String createTime;

    /**
     * Refund completion time, returned when transaction reaches terminal state (S/F). ISO 8601 format
     */
    private String completeTime;

    /**
     * Transaction result code
     */
    private String transactionResultCode;

    /**
     * Transaction result message
     */
    private String transactionResultMsg;

    /**
     * Refund description (returned as-is from request)
     */
    private String description;
}
