package com.sunmi.sunbay.nexus.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * Request for {@code POST /v1/checkout/expire-session} (Expire/close a checkout session).
 *
 * @see <a href="https://docs.sunbay-dev.com/zh/refspec/online/checkout/expire-session">Expire checkout session</a>
 * @since 2026-08-03
 */
@Data
@Builder
public class ExpireCheckoutSessionRequest {

    /**
     * Application ID assigned by SUNBAY (required)
     */
    private String appId;

    /**
     * Merchant ID assigned by SUNBAY (required)
     */
    private String merchantId;

    /**
     * The session ID to expire, as returned in the create-session response (required)
     */
    private String sessionId;

    /**
     * Optional reason for closing the session (optional)
     */
    private String reason;
}

