package com.sunmi.sunbay.nexus.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * Merchant terminals query request
 *
 * @since 2026-08-31
 */
@Data
@Builder
public class MerchantTerminalsQueryRequest {

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

    /**
     * Pagination token returned by the previous response.
     * Pass it back to fetch the next page. Omit on the first request.
     * The token is an opaque string — do not parse or modify its contents.
     */
    private String nextToken;
}
