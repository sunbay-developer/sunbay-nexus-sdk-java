package com.sunmi.sunbay.nexus.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * Void request
 *
 * @since 2025-12-12
 */
@Data
@Builder
public class VoidRequest {

    /**
     * Application ID
     */
    private String appId;

    /**
     * Merchant ID
     */
    private String merchantId;

    /**
     * Original transaction ID to void. Either originalTransactionId or originalTransactionRequestId is required. If both are provided, originalTransactionId takes priority
     */
    private String originalTransactionId;

    /**
     * Original transaction request ID to void. Either originalTransactionId or originalTransactionRequestId is required. If both are provided, originalTransactionId takes priority
     */
    private String originalTransactionRequestId;

    /**
     * Transaction request ID for this void transaction. Unique ID to identify this void request, used as API idempotency control field
     */
    private String transactionRequestId;

    /**
     * Void reason description
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
     * Receipt print option. Possible values: NONE, MERCHANT, CUSTOMER, BOTH. Default: "NONE"
     */
    private String printReceipt;
}
