package com.sunmi.sunbay.nexus.model.request;

import lombok.Builder;
import lombok.Data;

import com.sunmi.sunbay.nexus.model.common.AuthAmount;

/**
 * Incremental authorization request
 *
 * @since 2025-12-12
 */
@Data
@Builder
public class IncrementalAuthRequest {

    /**
     * Application ID
     */
    private String appId;

    /**
     * Merchant ID
     */
    private String merchantId;

    /**
     * Original authorization transaction ID to increase authorization amount. Either originalTransactionId or originalTransactionRequestId is required. If both are provided, originalTransactionId takes priority
     */
    private String originalTransactionId;

    /**
     * Original authorization transaction request ID to increase authorization amount. Either originalTransactionId or originalTransactionRequestId is required. If both are provided, originalTransactionId takes priority
     */
    private String originalTransactionRequestId;

    /**
     * Transaction request ID for this incremental authorization transaction. Unique ID to identify this incremental authorization request, used as API idempotency control field
     */
    private String transactionRequestId;

    /**
     * Amount information
     */
    private AuthAmount amount;

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
}
