package com.sunmi.sunbay.nexus.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * Merchant terminals query request (v2)
 *
 * @since 2026-10-14
 */
@Data
@Builder
public class MerchantTerminalsV2QueryRequest {

    /**
     * Application ID (required)
     */
    private String appId;

    /**
     * SUNBAY platform merchant unique identifier.
     * Format: 11-character alphanumeric string starting with M.
     * Note: This is not the MID assigned by a payment processor. (required)
     */
    private String merchantId;

    /**
     * Number of records returned per page. Range 1-200, default 100.
     * Values outside this range cause a parameter-error response. (optional)
     */
    private Integer size;

    /**
     * Pagination token returned by the previous response.
     * Pass it back to fetch the next page. Omit on the first request.
     * The token is an opaque string — do not parse or modify its contents. (optional)
     */
    private String nextToken;
}
