package com.sunmi.sunbay.nexus.model.request;

import lombok.Builder;
import lombok.Data;

import com.sunmi.sunbay.nexus.model.common.RefundAmount;
import com.sunmi.sunbay.nexus.model.common.PaymentMethodInfo;

/**
 * Refund request
 *
 * @since 2025-12-12
 */
@Data
@Builder
public class RefundRequest {

    /**
     * Application ID
     */
    private String appId;

    /**
     * Merchant ID
     */
    private String merchantId;

    /**
     * Original transaction ID to refund. Either originalTransactionId or originalTransactionRequestId is required for refund with reference. Both must be empty for refund without reference. If both are provided, originalTransactionId takes priority
     */
    private String originalTransactionId;

    /**
     * Original transaction request ID to refund. Either originalTransactionId or originalTransactionRequestId is required for refund with reference. Both must be empty for refund without reference. If both are provided, originalTransactionId takes priority
     */
    private String originalTransactionRequestId;

    /**
     * Reference order ID. Required for refund without reference, used to associate business records in merchant system. Not required for refund with reference, system will automatically associate with original transaction's reference order ID
     */
    private String referenceOrderId;

    /**
     * Transaction request ID for this refund transaction. Unique ID to identify this refund request, used as API idempotency control field
     */
    private String transactionRequestId;

    /**
     * Amount information
     */
    private RefundAmount amount;

    /**
     * Payment method information. Only available for refund without reference. Optional, recommended to omit for maximum flexibility
     */
    private PaymentMethodInfo paymentMethod;

    /**
     * Refund reason description. Should be a real description representing the refund reason
     */
    private String description;

    /**
     * Terminal serial number. SUNBAY provided financial POS device serial number for reading bank cards and processing PIN security operations
     */
    private String terminalSn;

    /**
     * Additional data, returned as-is, recommended to use JSON format
     */
    private String attach;

    /**
     * Asynchronous notification URL
     */
    private String notifyUrl;

    /**
     * Transaction expiration time, format: yyyy-MM-DDTHH:mm:ss+TIMEZONE (ISO 8601). Transaction will be closed if payment is not completed after this time. Minimum 3 minutes, maximum 1 day, default 1 day if not provided. Only used for refund without reference (requires customer operation on terminal), not needed for refund with reference
     */
    private String timeExpire;

    /**
     * Receipt print option. Possible values: NONE, MERCHANT, CUSTOMER, BOTH. Default: "NONE"
     */
    private String printReceipt;
}
