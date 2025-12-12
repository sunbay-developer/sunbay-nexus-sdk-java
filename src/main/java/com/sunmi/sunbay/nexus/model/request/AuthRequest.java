package com.sunmi.sunbay.nexus.model.request;

import com.sunmi.sunbay.nexus.model.common.Amount;
import com.sunmi.sunbay.nexus.model.common.PaymentMethodInfo;

/**
 * Authorization request
 *
 * @since 2025-12-12
 */
public class AuthRequest {

    private String appId;
    private String merchantId;
    private String referenceOrderId;
    private String transactionRequestId;
    private Amount amount;
    private PaymentMethodInfo paymentMethod;
    private String terminalSn;
    private String description;
    private String attach;
    private String notifyUrl;
    private String timeExpire;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getReferenceOrderId() {
        return referenceOrderId;
    }

    public void setReferenceOrderId(String referenceOrderId) {
        this.referenceOrderId = referenceOrderId;
    }

    public String getTransactionRequestId() {
        return transactionRequestId;
    }

    public void setTransactionRequestId(String transactionRequestId) {
        this.transactionRequestId = transactionRequestId;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public PaymentMethodInfo getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodInfo paymentMethod) {
        this.paymentMethod = paymentMethod;
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

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }
}
