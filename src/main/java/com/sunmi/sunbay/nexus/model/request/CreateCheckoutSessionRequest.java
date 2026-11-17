package com.sunmi.sunbay.nexus.model.request;

import java.util.List;

import lombok.Builder;
import lombok.Data;

import com.sunmi.sunbay.nexus.model.common.CheckoutAmount;
import com.sunmi.sunbay.nexus.model.common.CheckoutLineItem;

/**
 * Request for {@code POST /v1/checkout/create-session} (Hosted Payment Page).
 *
 * @see <a href="https://docs.sunbay.dev/en/refspec/online/checkout/checkout-api-integration">Create checkout session</a>
 * @since 2026-02-02
 */
@Data
@Builder
public class CreateCheckoutSessionRequest {

    /**
     * Required
     */
    private String appId;
    /**
     * Required
     */
    private String transactionRequestId;
    /**
     * Required
     */
    private String referenceOrderId;
    /**
     * Required
     */
    private String merchantId;
    /**
     * Required
     */
    private CheckoutAmount amount;
    /**
     * Required
     */
    private String description;
    /**
     * Optional
     */
    private List<CheckoutLineItem> productList;
    /**
     * Optional
     */
    private Boolean collectBillingAddress;
    /**
     * Optional
     */
    private Boolean collectShippingAddress;
    /**
     * Optional
     */
    private String merchantReturnUrl;
    /**
     * Optional
     */
    private String notifyUrl;
}
