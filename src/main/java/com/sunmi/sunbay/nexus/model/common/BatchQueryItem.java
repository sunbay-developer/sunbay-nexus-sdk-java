package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Batch query item information
 * <p>
 * Statistics grouped by channel code and price currency
 * </p>
 *
 * @since 2025-12-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchQueryItem {

    /**
     * Batch number
     */
    private String batchNo;

    /**
     * Batch start time, format: yyyy-MM-DDTHH:mm:ss+TIMEZONE (ISO 8601)
     */
    private String startTime;

    /**
     * Payment channel code
     */
    private String channelCode;

    /**
     * Price currency (ISO 4217)
     */
    private String priceCurrency;

    /**
     * Total count of transactions in the batch
     */
    private Integer totalCount;

    /**
     * Net amount, using smallest currency unit (minor units)
     */
    private Integer netAmount;

    /**
     * Tip amount, using smallest currency unit (minor units)
     */
    private Integer tipAmount;

    /**
     * Surcharge amount, using smallest currency unit (minor units)
     */
    private Integer surchargeAmount;

    /**
     * Tax amount, using smallest currency unit (minor units)
     */
    private Integer taxAmount;
}
