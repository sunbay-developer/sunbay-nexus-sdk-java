package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Batch close list item information
 * <p>
 * Represents a single closed (settled) batch record.
 * </p>
 *
 * @since 2026-08-31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchCloseListItem {

    /**
     * Batch number
     */
    private String batchNo;

    /**
     * Batch status: S - Success
     */
    private String batchStatus;

    /**
     * Batch close time, ISO 8601 format
     */
    private String batchTime;

    /**
     * Total number of transactions in the batch
     */
    private Integer totalCount;

    /**
     * Total net amount, using minor units.
     * The number of decimal places for each currency can refer to the ISO-4217 standard
     */
    private Integer netAmount;

    /**
     * Transaction currency (ISO 4217, e.g. USD, CNY)
     */
    private String priceCurrency;

    /**
     * Payment channel code
     */
    private String channelCode;

    /**
     * Terminal serial number
     */
    private String terminalSn;

    /**
     * Merchant Identification number (MID) assigned by the payment processor
     */
    private String mid;

    /**
     * Terminal Identification number (TID) assigned by the payment processor
     */
    private String tid;
}
