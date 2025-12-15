package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Post authorization amount information
 * Supports: orderAmount, tipAmount, taxAmount, surchargeAmount
 * Does NOT support: cashbackAmount
 *
 * @since 2025-12-15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostAuthAmount {

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
     * Pricing currency (ISO 4217, required)
     */
    private String pricingCurrency;
}
