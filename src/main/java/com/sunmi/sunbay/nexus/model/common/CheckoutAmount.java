package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Checkout amount breakdown used in request bodies (create session, direct payment). Payable total = orderAmount + taxAmount + surchargeAmount + tipAmount.
 *
 * @since 2026-02-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutAmount {

    /**
     * Order amount (smallest currency unit) (required)
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
     * Tip amount (optional)
     */
    private Integer tipAmount;

    /**
     * Total amount (optional)
     */
    private Integer totalAmount;

    /**
     * Price currency (ISO 4217) (required)
     */
    private String priceCurrency;
}
