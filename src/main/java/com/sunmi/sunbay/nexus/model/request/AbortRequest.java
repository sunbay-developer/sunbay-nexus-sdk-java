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
     * Application ID (required)
     */
    private String appId;

    /**
     * Merchant ID (required)
     */
    private String merchantId;

    /**
     * Original transaction ID to abort. Either originalTransactionId or originalTransactionRequestId should be provided. If both are provided, originalTransactionId takes priority (optional)
     */
    private String originalTransactionId;

    /**
     * Original transaction request ID to abort. Either originalTransactionId or originalTransactionRequestId should be provided. If both are provided, originalTransactionId takes priority (optional)
     */
    private String originalTransactionRequestId;

    /**
     * Terminal serial number. SUNBAY provided financial POS device serial number for reading bank cards and processing PIN security operations (required)
     */
    private String terminalSn;

    /**
     * Abort reason description (optional)
     */
    private String description;

    /**
     * Additional data, returned as-is, recommended to use JSON format (optional)
     */
    private String attach;
}
