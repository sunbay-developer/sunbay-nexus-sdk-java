package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;

/**
 * Batch close response
 *
 * @since 2025-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BatchCloseResponse extends BaseResponse {

    /**
     * Batch number
     */
    private String batchNo;

    /**
     * Terminal serial number
     */
    private String terminalSn;

    /**
     * Batch time, format: yyyy-MM-DDTHH:mm:ss+TIMEZONE (ISO 8601)
     */
    private String batchTime;

    /**
     * Number of transactions in the batch
     */
    private Integer transactionCount;

    /**
     * Price currency (ISO 4217)
     */
    private String priceCurrency;

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
