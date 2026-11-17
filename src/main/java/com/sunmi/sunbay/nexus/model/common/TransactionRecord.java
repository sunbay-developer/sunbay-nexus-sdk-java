package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A single transaction record returned in the list transactions response.
 *
 * @since 2026-10-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRecord {

    /**
     * SUNBAY Nexus transaction ID
     */
    private String transactionId;

    /**
     * Transaction request ID
     */
    private String transactionRequestId;

    /**
     * Merchant business order number
     */
    private String referenceOrderId;

    /**
     * Transaction status: INITIAL / PROCESSING / SUCCESS / FAIL / CLOSED
     */
    private String transactionStatus;

    /**
     * Related transaction status, indicates lifecycle changes caused by subsequent transactions.
     * Possible values: VOIDED / INCREMENTAL / REFUNDED / CAPTURE / PART_REFUNDED
     */
    private String relatedTransactionStatus;

    /**
     * Transaction type: SALE / AUTH / FORCED_AUTH / INCREMENTAL / POST_AUTH / VOID / REFUND
     */
    private String transactionType;

    /**
     * Amount details
     */
    private Amount amount;

    /**
     * Transaction creation time, format: yyyy-MM-DDTHH:mm:ss+TIMEZONE (ISO 8601)
     */
    private String createTime;

    /**
     * Transaction completion time, format: yyyy-MM-DDTHH:mm:ss+TIMEZONE (ISO 8601)
     */
    private String completeTime;

    /**
     * Masked card number (first 6 digits + ****** + last 4 digits)
     */
    private String maskedPan;

    /**
     * Card network type: CREDIT / DEBIT / EBT / EGC / UNKNOWN
     */
    private String cardNetworkType;

    /**
     * Payment method ID
     */
    private String paymentMethodId;

    /**
     * Sub payment method ID
     */
    private String subPaymentMethodId;

    /**
     * Batch number
     */
    private String batchNo;

    /**
     * Transaction batch settlement status.
     * N - No batch settlement needed; U - Waiting for batch close; C - Batch closed
     */
    private String transactionBatchStatus;

    /**
     * Voucher number
     */
    private String voucherNo;

    /**
     * System trace audit number
     */
    private String stan;

    /**
     * Retrieval reference number
     */
    private String rrn;

    /**
     * Authorization code
     */
    private String authCode;

    /**
     * Entry mode: MANUAL / SWIPE / FALLBACK_SWIPE / CONTACT / CONTACTLESS
     */
    private String entryMode;

    /**
     * Authentication method: NOT_AUTHENTICATED / PIN / OFFLINE_PIN / BY_PASS / SIGNATURE
     */
    private String authenticationMethod;

    /**
     * Transaction result code
     */
    private String transactionResultCode;

    /**
     * Transaction result message
     */
    private String transactionResultMsg;

    /**
     * Terminal serial number
     */
    private String terminalSn;

    /**
     * Payment description
     */
    private String description;

    /**
     * Additional data, returned as-is
     */
    private String attach;
}
