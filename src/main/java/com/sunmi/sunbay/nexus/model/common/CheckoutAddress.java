package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Billing or shipping address for online checkout (direct payment).
 *
 * @since 2026-02-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutAddress {

    /**
     * Optional
     */
    private String address;
    /**
     * Optional
     */
    private String city;
    /**
     * Optional
     */
    private String country;
    /**
     * Optional
     */
    private String postalCode;
    /**
     * Optional
     */
    private String state;
}
