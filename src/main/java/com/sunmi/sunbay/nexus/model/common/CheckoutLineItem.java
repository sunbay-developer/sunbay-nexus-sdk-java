package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Single entry in checkout {@code productList}. If sent, sum of amount × num must equal amount.orderAmount.
 *
 * @since 2026-02-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutLineItem {

    /**
     * Line amount (smallest currency unit)
     */
    private Integer amount;

    /**
     * Item name
     */
    private String name;

    /**
     * Quantity
     */
    private Integer num;
}
