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
     * Application ID (required)
     */
    private String appId;

    /**
     * Merchant ID (required)
     */
    private String merchantId;

    /**
     * Terminal serial number. (optional)
     *
     * @deprecated terminalSn is no longer required for tip adjustment and will be ignored by the server.
     */
    @Deprecated
    private String terminalSn;

    /**
     * Original transaction ID to adjust tip. Either originalTransactionId or originalTransactionRequestId should be provided. If both are provided, originalTransactionId takes priority (optional)
     */
    private String originalTransactionId;

    /**
     * Original transaction request ID to adjust tip. Either originalTransactionId or originalTransactionRequestId should be provided. If both are provided, originalTransactionId takes priority (optional)
     */
    private String originalTransactionRequestId;

    /**
     * New tip amount after adjustment, in basic currency unit (required)
     */
    private Integer tipAmount;

    /**
     * Additional data, returned as-is, recommended to use JSON format (optional)
     */
    private String attach;
}
