package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Online refund amount information (smallest currency unit).
 *
 * @since 2026-06-29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnlineRefundAmount {

    /**
     * Price currency (ISO 4217)
     */
    private String priceCurrency;

    /**
     * Total transaction amount (smallest currency unit)
     */
    private Integer totalAmount;

    /**
     * Order amount (smallest currency unit)
     */
    private Integer orderAmount;

    /**
     * Tax amount (smallest currency unit)
     */
    private Integer taxAmount;

    /**
     * Surcharge amount (smallest currency unit)
     */
    private Integer surchargeAmount;

    /**
     * Tip amount (smallest currency unit)
     */
    private Integer tipAmount;
}
