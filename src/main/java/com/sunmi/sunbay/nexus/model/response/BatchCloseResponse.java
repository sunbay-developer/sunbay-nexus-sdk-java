package com.sunmi.sunbay.nexus.model.response;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;
import com.sunmi.sunbay.nexus.model.common.BatchTotalAmount;

/**
 * Batch close response
 *
 * @since 2025-12-10
 */
public class BatchCloseResponse extends BaseResponse {

    private String batchNo;
    private String terminalSn;
    private String closeTime;
    private Integer transactionCount;
    private BatchTotalAmount totalAmount;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getTerminalSn() {
        return terminalSn;
    }

    public void setTerminalSn(String terminalSn) {
        this.terminalSn = terminalSn;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public Integer getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Integer transactionCount) {
        this.transactionCount = transactionCount;
    }

    public BatchTotalAmount getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BatchTotalAmount totalAmount) {
        this.totalAmount = totalAmount;
    }
}
