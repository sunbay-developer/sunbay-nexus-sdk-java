package com.sunmi.sunbay.nexus.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * Batch close list request
 * <p>
 * Query closed (settled) batch records. You can filter results by payment channel
 * and time range. If no time range is specified, the API returns data from the last
 * 7 days by default. The maximum query span is 30 days.
 * </p>
 *
 * @since 2026-08-31
 */
@Data
@Builder
public class BatchCloseListRequest {

    /**
     * Application ID (required)
     */
    private String appId;

    /**
     * Merchant ID (required)
     */
    private String merchantId;

    /**
     * Payment terminal serial number. The payment terminal device serial number provided by SUNBAY (required)
     */
    private String terminalSn;

    /**
     * Payment channel code. If specified, only returns batches for this channel (optional)
     */
    private String channelCode;

    /**
     * Query start time, ISO 8601 format.
     * startTime and endTime must both be present.
     * The time span cannot exceed 30 days.
     * If not specified, defaults to the last 7 days (optional)
     */
    private String startTime;

    /**
     * Query end time, ISO 8601 format.
     * startTime and endTime must both be present.
     * The time span cannot exceed 30 days.
     * If not specified, defaults to the last 7 days (optional)
     */
    private String endTime;
}
