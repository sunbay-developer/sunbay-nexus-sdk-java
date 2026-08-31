package com.sunmi.sunbay.nexus.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * Merchant query request
 *
 * @since 2026-08-31
 */
@Data
@Builder
public class MerchantQueryRequest {

    /**
     * Application ID
     */
    private String appId;

    /**
     * SUNBAY platform merchant unique identifier.
     * Format: 11-character alphanumeric string starting with M.
     * Note: This is not the MID assigned by a payment processor
     */
    private String merchantId;
}
