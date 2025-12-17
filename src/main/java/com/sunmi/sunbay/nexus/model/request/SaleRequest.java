package com.sunmi.sunbay.nexus.model.request;

import lombok.Builder;
import lombok.Data;

import com.sunmi.sunbay.nexus.model.common.SaleAmount;
import com.sunmi.sunbay.nexus.model.common.PaymentMethodInfo;

/**
 * Sale transaction request
 *
 * @since 2025-12-12
 */
@Data
@Builder
public class SaleRequest {

    /**
     * Application ID
     */
    private String appId;

    /**
     * Merchant ID
     */
    private String merchantId;

    /**
     * Reference order ID for the sale transaction. Unique ID assigned by merchant system to identify this sale transaction, 6-32 characters, can only contain numbers, uppercase/lowercase letters, _-\|*
     */
    private String referenceOrderId;

    /**
     * Unique request identifier for this sale transaction. Used as the API idempotency key to ensure that retrying the
     * same request does not create multiple transactions. Can only contain letters, digits, underscore (_) and hyphen (-),
     * with a maximum length of 64 characters. Each request must use a value that has not been used before.
     */
    private String transactionRequestId;

    /**
     * Amount information
     */
    private SaleAmount amount;

    /**
     * Payment method information. Optional, recommended to omit for maximum flexibility
     */
    private PaymentMethodInfo paymentMethod;

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
     * Transaction expiration time, format: yyyy-MM-DDTHH:mm:ss+TIMEZONE (ISO 8601). Transaction will be closed if payment is not completed after this time. Minimum 3 minutes, maximum 1 day, default 1 day if not provided
     */
    private String timeExpire;
}
