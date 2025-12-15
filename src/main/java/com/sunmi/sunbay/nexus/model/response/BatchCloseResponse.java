package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;
import com.sunmi.sunbay.nexus.model.common.BatchTotalAmount;

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
     * Batch close time, format: yyyy-MM-DDTHH:mm:ss+TIMEZONE (ISO 8601)
     */
    private String closeTime;

    /**
     * Number of transactions in the batch
     */
    private Integer transactionCount;

    /**
     * Total amount of the batch
     */
    private BatchTotalAmount totalAmount;
}
