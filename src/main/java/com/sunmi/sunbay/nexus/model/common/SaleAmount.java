package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Sale transaction amount information
 * Supports: orderAmount, tipAmount, taxAmount, surchargeAmount, cashbackAmount
 *
 * @since 2025-12-15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleAmount {

    /**
     * Order amount (required)
     */
    private Integer orderAmount;

    /**
     * Tip amount (optional)
     */
    private Integer tipAmount;

    /**
     * Tax amount (optional)
     */
    private Integer taxAmount;

    /**
     * Surcharge amount (optional)
     */
    private Integer surchargeAmount;

    /**
     * Cashback amount (optional)
     */
    private Integer cashbackAmount;

    /**
     * Pricing currency (ISO 4217, required)
     */
    private String pricingCurrency;
}
