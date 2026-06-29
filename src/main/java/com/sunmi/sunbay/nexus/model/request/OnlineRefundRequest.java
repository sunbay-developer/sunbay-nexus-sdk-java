package com.sunmi.sunbay.nexus.model.request;

import lombok.Builder;
import lombok.Data;

import com.sunmi.sunbay.nexus.model.common.OnlineRefundAmount;

/**
 * Online refund request ({@code POST /v1/checkout/refund}).
 * <p>
 * Either {@code originalTransactionId} or {@code originalTransactionRequestId} must be provided
 * to identify the original transaction to refund.
 * </p>
 *
 * @since 2026-06-29
 */
@Data
@Builder
public class OnlineRefundRequest {

    /**
     * Application ID
     */
    private String appId;

    /**
     * Merchant ID
     */
    private String merchantId;

    /**
     * Transaction request ID for this refund transaction.
     * Unique ID to identify this refund request, used as API idempotency control field.
     * Only letters, numbers, underscores and hyphens are supported, max length 64.
     */
    private String transactionRequestId;

    /**
     * Original transaction ID to refund (SUNBAY transaction ID from the payment response).
     * Either originalTransactionId or originalTransactionRequestId is required.
     * If both are provided, originalTransactionId takes priority.
     */
    private String originalTransactionId;

    /**
     * Original transaction request ID to refund.
     * Either originalTransactionId or originalTransactionRequestId is required.
     * If both are provided, originalTransactionId takes priority.
     */
    private String originalTransactionRequestId;

    /**
     * Refund amount information.
     * If totalAmount is provided, system will validate it equals orderAmount + taxAmount + surchargeAmount + tipAmount.
     */
    private OnlineRefundAmount amount;

    /**
     * Refund description
     */
    private String description;

    /**
     * Additional data, returned as-is, can be used to record refund reason or other custom information
     */
    private String attach;

    /**
     * Asynchronous notification URL (Webhook). Must be a publicly accessible HTTPS address if provided.
     */
    private String notifyUrl;
}
