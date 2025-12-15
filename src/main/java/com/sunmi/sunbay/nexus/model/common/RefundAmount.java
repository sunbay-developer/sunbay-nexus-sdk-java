package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Refund amount information
 * Supports: orderAmount, pricingCurrency only
 *
 * @since 2025-12-15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundAmount {

    /**
     * Order amount (required)
     */
    private Double orderAmount;

    /**
     * Pricing currency (ISO 4217, required)
     */
    private String pricingCurrency;
}
