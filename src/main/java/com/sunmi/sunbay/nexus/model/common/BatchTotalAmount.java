package com.sunmi.sunbay.nexus.model.common;

/**
 * Batch total amount information
 *
 * @since 2025-12-12
 */
public class BatchTotalAmount {

    /**
     * Price currency (ISO 4217)
     */
    private String priceCurrency;

    /**
     * Total amount
     */
    private Double amount;

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

