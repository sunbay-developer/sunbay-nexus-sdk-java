package com.sunmi.sunbay.nexus.model.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * List transactions request
 *
 * <p>Two query modes are supported (mutually exclusive):
 * <ul>
 *   <li>By time range: pass {@code startTime} and {@code endTime} (span cannot exceed 7 days)</li>
 *   <li>By batch: pass {@code batchNo}</li>
 * </ul>
 *
 * @since 2026-10-14
 */
@Data
@Builder
public class ListTransactionsRequest {

    /**
     * Application ID (required)
     */
    private String appId;

    /**
     * SUNBAY platform merchant unique identifier.
     * Format: 11-character alphanumeric string starting with M.
     * Note: This is not the MID assigned by a payment processor.
     * Querying by merchantId returns both in-person and online transactions for that merchant. (required)
     */
    private String merchantId;

    /**
     * Start of the query time range (inclusive), ISO 8601 with timezone.
     * Used when querying by time range (use together with endTime; mutually exclusive with batchNo).
     * Format: yyyy-MM-DDTHH:mm:ss+TIMEZONE (optional)
     */
    private String startTime;

    /**
     * End of the query time range (inclusive), ISO 8601 with timezone.
     * Used when querying by time range (use together with startTime; mutually exclusive with batchNo).
     * The span between startTime and endTime cannot exceed 7 days.
     * Format: yyyy-MM-DDTHH:mm:ss+TIMEZONE (optional)
     */
    private String endTime;

    /**
     * Batch number to query by.
     * Used when querying by batch (mutually exclusive with startTime / endTime). (optional)
     */
    private String batchNo;

    /**
     * Filter by transaction status. Omit to disable this filter.
     * (optional)
     */
    private String txnStatus;

    /**
     * Filter by transaction type; supports multiple values.
     * Uses form style with explode in the query string (e.g. txnTypeArray=SALE&txnTypeArray=REFUND).
     * Omit to disable this filter.
     * (optional)
     */
    private List<String> txnTypeArray;

    /**
     * Filter by terminal serial number. Omit to disable this filter. (optional)
     */
    private String terminalSn;

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
