package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Checkout amount breakdown. Payable total = orderAmount + taxAmount + surchargeAmount.
 *
 * @since 2026-02-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutAmount {

    /**
     * Order amount (smallest currency unit)
     */
    private Integer orderAmount;

    /**
     * Tax amount (optional)
     */
    private Integer taxAmount;

    /**
     * Surcharge amount (optional)
     */
    private Integer surchargeAmount;

    /**
     * Price currency (ISO 4217)
     */
    private String priceCurrency;
}
