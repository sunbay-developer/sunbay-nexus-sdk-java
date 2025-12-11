package com.sunmi.sunbay.model.response;

import com.sunmi.sunbay.model.common.Amount;
import com.sunmi.sunbay.model.common.BaseResponse;

/**
 * Query response
 *
 * @since 2025-12-10
 */
public class QueryResponse extends BaseResponse {

    private String transactionId;
    private String transactionRequestId;
    private String referenceOrderId;
    private String transactionStatus;
    private String transactionType;
    private Amount amount;
    private String createTime;
    private String completeTime;
    private String maskedPan;
    private String cardNetworkType;
    private String paymentMethodId;
    private String subPaymentMethodId;
    private Integer batchNo;
    private String voucherNo;
    private String stan;
    private String rrn;
    private String authCode;
    private String entryMode;
    private String authenticationMethod;
    private String transactionResultCode;
    private String transactionResultMsg;
    private String terminalSn;
    private String description;
    private String attach;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionRequestId() {
        return transactionRequestId;
    }

    public void setTransactionRequestId(String transactionRequestId) {
        this.transactionRequestId = transactionRequestId;
    }

    public String getReferenceOrderId() {
        return referenceOrderId;
    }

    public void setReferenceOrderId(String referenceOrderId) {
        this.referenceOrderId = referenceOrderId;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public String getMaskedPan() {
        return maskedPan;
    }

    public void setMaskedPan(String maskedPan) {
        this.maskedPan = maskedPan;
    }

    public String getCardNetworkType() {
        return cardNetworkType;
    }

    public void setCardNetworkType(String cardNetworkType) {
        this.cardNetworkType = cardNetworkType;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getSubPaymentMethodId() {
        return subPaymentMethodId;
    }

    public void setSubPaymentMethodId(String subPaymentMethodId) {
        this.subPaymentMethodId = subPaymentMethodId;
    }

    public Integer getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(Integer batchNo) {
        this.batchNo = batchNo;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public String getStan() {
        return stan;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getEntryMode() {
        return entryMode;
    }

    public void setEntryMode(String entryMode) {
        this.entryMode = entryMode;
    }

    public String getAuthenticationMethod() {
        return authenticationMethod;
    }

    public void setAuthenticationMethod(String authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
    }

    public String getTransactionResultCode() {
        return transactionResultCode;
    }

    public void setTransactionResultCode(String transactionResultCode) {
        this.transactionResultCode = transactionResultCode;
    }

    public String getTransactionResultMsg() {
        return transactionResultMsg;
    }

    public void setTransactionResultMsg(String transactionResultMsg) {
        this.transactionResultMsg = transactionResultMsg;
    }

    public String getTerminalSn() {
        return terminalSn;
    }

    public void setTerminalSn(String terminalSn) {
        this.terminalSn = terminalSn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }
}
