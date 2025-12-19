package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Refund amount information
 * Supports: orderAmount, tipAmount, taxAmount, surchargeAmount, cashbackAmount
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
     * Tip amount (optional, must be greater than or equal to 0)
     */
    private Double tipAmount;

    /**
     * Tax amount (optional, must be greater than or equal to 0)
     */
    private Double taxAmount;

    /**
     * Surcharge amount (optional, must be greater than or equal to 0).
     * Note: Some processors may require surcharge to be refunded proportionally. Please contact technical support for detailed policies.
     */
    private Double surchargeAmount;

    /**
     * Cashback amount (optional, must be greater than or equal to 0)
     */
    private Double cashbackAmount;

    /**
     * Pricing currency (ISO 4217, required)
     */
    private String pricingCurrency;
}
