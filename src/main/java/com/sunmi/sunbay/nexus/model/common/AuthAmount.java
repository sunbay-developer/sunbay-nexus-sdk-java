package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Authorization amount information
 * Supports: orderAmount, pricingCurrency only
 * Used for: Auth, ForcedAuth, IncrementalAuth
 *
 * @since 2025-12-15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthAmount {

    /**
     * Order amount (required)
     */
    private Double orderAmount;

    /**
     * Pricing currency (ISO 4217, required)
     */
    private String pricingCurrency;
}
