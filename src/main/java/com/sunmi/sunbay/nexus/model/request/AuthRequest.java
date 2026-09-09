package com.sunmi.sunbay.nexus.model.request;

import lombok.Builder;
import lombok.Data;

import com.sunmi.sunbay.nexus.model.common.AuthAmount;
import com.sunmi.sunbay.nexus.model.common.PaymentMethodInfo;
import com.sunmi.sunbay.nexus.model.common.SignatureConfig;

/**
 * Authorization request
 *
 * @since 2025-12-12
 */
@Data
@Builder
public class AuthRequest {

    /**
     * Application ID
     */
    private String appId;

    /**
     * Merchant ID
     */
    private String merchantId;

    /**
     * Reference order ID for the authorization transaction. Unique ID assigned by merchant system to identify this authorization transaction, 6-32 characters, can only contain numbers, uppercase/lowercase letters, _-\|*
     */
    private String referenceOrderId;

    /**
     * Transaction request ID for this authorization transaction. Unique ID to identify this authorization transaction request, used as API idempotency control field
     */
    private String transactionRequestId;

    /**
     * Amount information
     */
    private AuthAmount amount;

    /**
     * Payment method information. Optional, recommended to omit for maximum flexibility
     */
    private PaymentMethodInfo paymentMethod;

    /**
     * Card network type. Only effective when paymentMethod.category is CARD; when not specified, system will auto-detect. See {@link com.sunmi.sunbay.nexus.enums.CardNetworkType}
     */
    private String cardNetworkType;

    /**
     * Product description. Should be a real description representing the product information, may be displayed on some payment App billing pages
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
     * Terminal event asynchronous notification URL. When provided, real-time terminal status events
     * (card swipe, signature, printing, etc.) will be pushed to this URL during the transaction process.
     */
    private String terminalEventNotifyUrl;

    /**
     * Transaction expiration time, format: yyyy-MM-DDTHH:mm:ss+TIMEZONE (ISO 8601). Transaction will be closed if payment is not completed after this time. Minimum 3 minutes, maximum 1 day, default 1 day if not provided
     */
    private String timeExpire;

    /**
     * Receipt print option. Possible values: NONE, MERCHANT, CUSTOMER, BOTH. Default: "NONE"
     */
    private String printReceipt;

    /**
     * Signature entry location. Possible values: ON_SCREEN (sign on terminal screen),
     * ON_RECEIPT (sign on receipt), NONE (no signature). When not provided, the
     * SUNBAY platform default configuration is used.
     *
     * @deprecated Use {@link #signatureConfig} instead
     */
    @Deprecated
    private String signatureEntryLocation;

    /**
     * Signature configuration. Controls signature collection behavior after transaction completion.
     * When not provided, the SUNBAY platform signature configuration is used by default.
     * This field replaces the deprecated signatureEntryLocation.
     */
    private SignatureConfig signatureConfig;
}
