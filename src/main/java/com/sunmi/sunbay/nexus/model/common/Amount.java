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
     * Price currency (ISO 4217)
     */
    private String priceCurrency;

    /**
     * Transaction amount (calculated field in response)
     */
    private Integer transAmount;

    /**
     * Order amount
     */
    private Integer orderAmount;

    /**
     * Tax amount
     */
    private Integer taxAmount;

    /**
     * Surcharge amount
     */
    private Integer surchargeAmount;

    /**
     * Tip amount
     */
    private Integer tipAmount;

    /**
     * Cashback amount
     */
    private Integer cashbackAmount;
}
