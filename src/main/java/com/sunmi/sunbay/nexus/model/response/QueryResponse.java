package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.Amount;
import com.sunmi.sunbay.nexus.model.common.BaseResponse;

/**
 * Query response
 *
 * @since 2025-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryResponse extends BaseResponse {

    /**
     * SUNBAY Nexus transaction ID
     */
    private String transactionId;

    /**
     * Transaction request ID
     */
    private String transactionRequestId;

    /**
     * Reference order ID (only returned for transactions with reference order ID such as sale, authorization, forced authorization)
     */
    private String referenceOrderId;

    /**
     * Transaction status: INITIAL(initial)/PROCESSING(processing)/SUCCESS(success)/FAIL(failed)/CLOSED(closed)
     */
    private String transactionStatus;

    /**
     * Transaction type: AUTH(authorization)/SALE(sale)/FORCED_AUTH(forced authorization)/INCREMENTAL(incremental authorization)/POST_AUTH(post authorization)/VOID(void)/REFUND(refund)
     */
    private String transactionType;

    /**
     * Transaction amount details
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
     * Masked card number (first 6 digits + **** + last 4 digits)
     */
    private String maskedPan;

    /**
     * Card network type: CREDIT(credit card)/DEBIT(debit card)/EBT/EGC/UNKNOWN
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
     * Voucher number
     */
    private String voucherNo;

    /**
     * System trace number
     */
    private String stan;

    /**
     * Reference number
     */
    private String rrn;

    /**
     * Authorization code
     */
    private String authCode;

    /**
     * Entry mode: MANUAL(manual input)/SWIPE(swipe)/FALLBACK_SWIPE(swipe fallback)/CONTACT(contact)/CONTACTLESS(contactless)
     */
    private String entryMode;

    /**
     * Authentication method: NOT_AUTHENTICATED(not authenticated)/PIN/OFFLINE_PIN/BY_PASS/SIGNATURE
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
     * Product description
     */
    private String description;

    /**
     * Additional data, returned as-is
     */
    private String attach;
}
