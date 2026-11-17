package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Amount returned in the create checkout session response ({@code POST /v1/checkout/create-session}).
 * The response contract only carries these four fields, while the request amount
 * ({@link CheckoutAmount}) also carries tipAmount and totalAmount, so a dedicated model is used.
 *
 * @author andy.li, kiro
 * @since 2026-11-17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutSessionAmount {

    /**
     * Order amount (smallest currency unit)
     */
    private Integer orderAmount;

    /**
     * Price currency (ISO 4217)
     */
    private String priceCurrency;

    /**
     * Surcharge amount
     */
    private Integer surchargeAmount;

    /**
     * Tax amount
     */
    private Integer taxAmount;
}
