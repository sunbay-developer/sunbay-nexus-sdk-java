package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;

/**
 * Response for create checkout session.
 *
 * @since 2026-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateCheckoutSessionResponse extends BaseResponse {

    /**
     * URL to redirect the customer to the Hosted Payment Page
     */
    private String checkoutUrl;

    /**
     * Session expiry time (e.g. ISO 8601); session lifetime is 30 minutes from a successful response
     */
    private String expiresAt;
}
