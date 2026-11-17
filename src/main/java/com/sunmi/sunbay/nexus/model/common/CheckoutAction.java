package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Next step the customer must take for online direct payment ({@code POST /v1/checkout/sale}),
 * e.g. a 3DS redirect. Returned only when an action is required.
 *
 * @author andy.li, kiro
 * @since 2026-11-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutAction {

    /**
     * Action type: REDIRECT / QR_CODE
     */
    private String type;

    /**
     * URL to redirect the customer to (for type REDIRECT)
     */
    private String redirectUrl;
}
