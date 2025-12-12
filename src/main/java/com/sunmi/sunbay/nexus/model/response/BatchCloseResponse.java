package com.sunmi.sunbay.nexus.model.response;

import com.sunmi.sunbay.nexus.model.common.Amount;
import com.sunmi.sunbay.nexus.model.common.BaseResponse;

/**
 * Batch close response
 *
 * @since 2025-12-10
 */
public class BatchCloseResponse extends BaseResponse {

    private Integer batchNo;
    private String terminalSn;
    private String closeTime;
    private Integer transactionCount;
    private Amount totalAmount;

    public Integer getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(Integer batchNo) {
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

    public Amount getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Amount totalAmount) {
        this.totalAmount = totalAmount;
    }
}
