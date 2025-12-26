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
     * Pricing currency (ISO 4217, required)
     */
    private String pricingCurrency;
}
