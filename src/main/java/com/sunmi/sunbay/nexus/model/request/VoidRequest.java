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
     * Application ID (required)
     */
    private String appId;

    /**
     * Merchant ID (required)
     */
    private String merchantId;

    /**
     * Original transaction ID to void. Either originalTransactionId or originalTransactionRequestId should be provided. If both are provided, originalTransactionId takes priority (optional)
     */
    private String originalTransactionId;

    /**
     * Original transaction request ID to void. Either originalTransactionId or originalTransactionRequestId should be provided. If both are provided, originalTransactionId takes priority (optional)
     */
    private String originalTransactionRequestId;

    /**
     * Transaction request ID for this void transaction. Unique ID to identify this void request, used as API idempotency control field (required)
     */
    private String transactionRequestId;

    /**
     * Void reason description (required)
     */
    private String description;

    /**
     * Terminal serial number. SUNBAY provided financial POS device serial number for reading bank cards and processing PIN security operations.
     * Only used when pushToTerminal is true. Should be omitted when pushToTerminal is false. (optional)
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
     * (card swipe, signature, printing, etc.) will be pushed to this URL during the transaction process.
     * Only effective when pushToTerminal is true. (optional)
     */
    private String terminalEventNotifyUrl;

    /**
     * Receipt print option. Possible values: NONE, MERCHANT, CUSTOMER, BOTH, AUTO. Default: "NONE" (optional)
     */
    private String printReceipt;

    /**
     * Whether to push the transaction to the terminal. Server default: true.
     * When not set, the field is not sent and the server default applies. (optional)
     */
    private Boolean pushToTerminal;
}