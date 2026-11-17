package com.sunmi.sunbay.nexus.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * Batch query request
 *
 * @since 2025-12-26
 */
@Data
@Builder
public class BatchQueryRequest {

    /**
     * Application ID (required)
     */
    private String appId;

    /**
     * Merchant ID (required)
     */
    private String merchantId;

    /**
     * Terminal serial number. SUNBAY provided financial POS device serial number (required)
     */
    private String terminalSn;
}

