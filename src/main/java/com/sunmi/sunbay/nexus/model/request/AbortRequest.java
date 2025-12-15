package com.sunmi.sunbay.nexus.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * Abort request
 *
 * @since 2025-12-10
 */
@Data
@Builder
public class AbortRequest {

    /**
     * Application ID
     */
    private String appId;

    /**
     * Merchant ID
     */
    private String merchantId;

    /**
     * Original transaction ID to abort. Either originalTransactionId or originalTransactionRequestId is required. If both are provided, originalTransactionId takes priority
     */
    private String originalTransactionId;

    /**
     * Original transaction request ID to abort. Either originalTransactionId or originalTransactionRequestId is required. If both are provided, originalTransactionId takes priority
     */
    private String originalTransactionRequestId;

    /**
     * Terminal serial number. SUNBAY provided financial POS device serial number for reading bank cards and processing PIN security operations
     */
    private String terminalSn;

    /**
     * Abort reason description
     */
    private String description;

    /**
     * Additional data, returned as-is, recommended to use JSON format
     */
    private String attach;
}
