package com.sunmi.sunbay.nexus.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * Tip adjust request
 *
 * @since 2025-12-12
 */
@Data
@Builder
public class TipAdjustRequest {

    /**
     * Application ID
     */
    private String appId;

    /**
     * Merchant ID
     */
    private String merchantId;

    /**
     * Terminal serial number
     */
    private String terminalSn;

    /**
     * Original transaction ID to adjust tip. Either originalTransactionId or originalTransactionRequestId is required. If both are provided, originalTransactionId takes priority
     */
    private String originalTransactionId;

    /**
     * Original transaction request ID to adjust tip. Either originalTransactionId or originalTransactionRequestId is required. If both are provided, originalTransactionId takes priority
     */
    private String originalTransactionRequestId;

    /**
     * New tip amount after adjustment, in basic currency unit
     */
    private Integer tipAmount;

    /**
     * Additional data, returned as-is, recommended to use JSON format
     */
    private String attach;
}
