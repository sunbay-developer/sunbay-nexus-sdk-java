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
    private Double orderAmount;

    /**
     * Tip amount (optional)
     */
    private Double tipAmount;

    /**
     * Tax amount (optional)
     */
    private Double taxAmount;

    /**
     * Surcharge amount (optional)
     */
    private Double surchargeAmount;

    /**
     * Cashback amount (optional)
     */
    private Double cashbackAmount;

    /**
     * Pricing currency (ISO 4217, required)
     */
    private String pricingCurrency;
}
