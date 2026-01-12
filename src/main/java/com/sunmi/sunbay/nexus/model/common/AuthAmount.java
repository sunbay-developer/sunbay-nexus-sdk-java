package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Authorization amount information
 * Supports: orderAmount, priceCurrency only
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
    private Integer orderAmount;

    /**
     * Price currency (ISO 4217, required)
     */
    private String priceCurrency;
}
