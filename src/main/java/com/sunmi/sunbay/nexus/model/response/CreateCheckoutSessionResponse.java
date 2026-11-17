package com.sunmi.sunbay.nexus.model.response;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;
import com.sunmi.sunbay.nexus.model.common.CheckoutSessionAmount;
import com.sunmi.sunbay.nexus.model.common.CheckoutLineItem;

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

    /**
     * Checkout session ID
     */
    private String sessionId;

    /**
     * Application ID
     */
    private String appId;

    /**
     * Merchant ID
     */
    private String merchantId;

    /**
     * Transaction request ID (echoed from the request)
     */
    private String transactionRequestId;

    /**
     * SUNBAY transaction ID
     */
    private String transactionId;

    /**
     * Merchant reference order ID (echoed from the request)
     */
    private String referenceOrderId;

    /**
     * Amount breakdown
     */
    private CheckoutSessionAmount amount;

    /**
     * Order description
     */
    private String description;

    /**
     * Cart line items
     */
    private List<CheckoutLineItem> productList;

    /**
     * Whether billing address is collected on checkout
     */
    private Boolean collectBillingAddress;

    /**
     * Whether shipping address is collected on checkout
     */
    private Boolean collectShippingAddress;

    /**
     * Return URL after payment completes or is cancelled
     */
    private String merchantReturnUrl;

    /**
     * Async webhook URL
     */
    private String notifyUrl;
}
