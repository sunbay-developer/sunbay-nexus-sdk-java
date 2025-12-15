package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Amount information
 *
 * @since 2025-12-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Amount {

    /**
     * Price currency (used in query response, ISO 4217)
     */
    private String priceCurrency;

    /**
     * Transaction amount (calculated field in response)
     */
    private Double transAmount;

    /**
     * Order amount
     */
    private Double orderAmount;

    /**
     * Tax amount
     */
    private Double taxAmount;

    /**
     * Surcharge amount
     */
    private Double surchargeAmount;

    /**
     * Tip amount
     */
    private Double tipAmount;

    /**
     * Cashback amount
     */
    private Double cashbackAmount;

    /**
     * Pricing currency (ISO 4217, used in request)
     */
    private String pricingCurrency;
}
