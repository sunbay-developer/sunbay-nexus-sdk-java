package com.sunmi.sunbay.nexus.model.common;

/**
 * Amount information
 *
 * @since 2025-12-12
 */
public class Amount {

    /**
     * Order amount
     */
    private Double orderAmount;

    /**
     * Pricing currency (ISO 4217)
     */
    private String pricingCurrency;

    /**
     * Tip amount
     */
    private Double tipAmount;

    /**
     * Enable screen tip input
     */
    private Boolean enableScreenTip;

    /**
     * Tax amount
     */
    private Double taxAmount;

    /**
     * Surcharge amount
     */
    private Double surchargeAmount;

    /**
     * Cashback amount
     */
    private Double cashbackAmount;

    /**
     * Transaction amount (calculated field in response)
     */
    private Double transAmount;

    /**
     * Price currency (used in query response, ISO 4217)
     */
    private String priceCurrency;

    /**
     * Create Amount with order amount and currency
     *
     * @param orderAmount     order amount
     * @param pricingCurrency pricing currency
     * @return Amount instance
     */
    public static Amount of(Double orderAmount, String pricingCurrency) {
        Amount amount = new Amount();
        amount.setOrderAmount(orderAmount);
        amount.setPricingCurrency(pricingCurrency);
        return amount;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPricingCurrency() {
        return pricingCurrency;
    }

    public void setPricingCurrency(String pricingCurrency) {
        this.pricingCurrency = pricingCurrency;
    }

    public Double getTipAmount() {
        return tipAmount;
    }

    public void setTipAmount(Double tipAmount) {
        this.tipAmount = tipAmount;
    }

    public Boolean getEnableScreenTip() {
        return enableScreenTip;
    }

    public void setEnableScreenTip(Boolean enableScreenTip) {
        this.enableScreenTip = enableScreenTip;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getSurchargeAmount() {
        return surchargeAmount;
    }

    public void setSurchargeAmount(Double surchargeAmount) {
        this.surchargeAmount = surchargeAmount;
    }

    public Double getCashbackAmount() {
        return cashbackAmount;
    }

    public void setCashbackAmount(Double cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }

    public Double getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Double transAmount) {
        this.transAmount = transAmount;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }
}
