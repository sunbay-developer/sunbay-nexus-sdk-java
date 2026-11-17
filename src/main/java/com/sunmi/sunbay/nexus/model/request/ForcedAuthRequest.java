package com.sunmi.sunbay.nexus.model.request;

import lombok.Builder;
import lombok.Data;

import com.sunmi.sunbay.nexus.model.common.AuthAmount;
import com.sunmi.sunbay.nexus.model.common.PaymentMethodInfo;

/**
 * Forced authorization request
 *
 * @since 2025-12-12
 */
@Data
@Builder
public class ForcedAuthRequest {

    /**
     * Application ID (required)
     */
    private String appId;

    /**
     * Merchant ID (required)
     */
    private String merchantId;

    /**
     * Reference order ID for the forced authorization transaction. Unique ID assigned by merchant system to identify this forced authorization transaction, 6-32 characters, can only contain numbers, uppercase/lowercase letters, _-\|* (required)
     */
    private String referenceOrderId;

    /**
     * Transaction request ID for this forced authorization transaction. Unique ID to identify this forced authorization transaction request, used as API idempotency control field (required)
     */
    private String transactionRequestId;

    /**
     * Amount information (required)
     */
    private AuthAmount amount;

    /**
     * Payment method information. Optional, recommended to omit for maximum flexibility
     */
    private PaymentMethodInfo paymentMethod;

    /**
     * Card network type. Only effective when paymentMethod.category is CARD; when not specified, system will auto-detect. (optional)
     */
    private String cardNetworkType;

    /**
     * Product description. Should be a real description representing the product information, may be displayed on some payment App billing pages (required)
     */
    private String description;

    /**
     * Terminal serial number. SUNBAY provided financial POS device serial number for reading bank cards and processing PIN security operations (required)
     */
    private String terminalSn;

    /**
     * Additional data, returned as-is, recommended to use JSON format (optional)
     */
    private String attach;

    /**
     * Asynchronous notification URL (optional)
     */
    private String notifyUrl;

    /**
     * Terminal event asynchronous notification URL. When provided, real-time terminal status events
     * (card swipe, signature, printing, etc.) will be pushed to this URL during the transaction process. (optional)
     */
    private String terminalEventNotifyUrl;

    /**
     * Transaction expiration time, format: yyyy-MM-DDTHH:mm:ss+TIMEZONE (ISO 8601). Transaction will be closed if payment is not completed after this time. Minimum 3 minutes, maximum 1 day, default 1 day if not provided (optional)
     */
    private String timeExpire;

    /**
     * Receipt print option. Possible values: NONE, MERCHANT, CUSTOMER, BOTH, AUTO. Default: "NONE" (optional)
     */
    private String printReceipt;
}
