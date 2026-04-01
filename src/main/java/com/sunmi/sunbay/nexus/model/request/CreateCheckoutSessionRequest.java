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

    private String appId;
    private String transactionRequestId;
    private String referenceOrderId;
    private String merchantId;
    private CheckoutAmount amount;
    private String description;
    private List<CheckoutLineItem> productList;
    private Boolean collectBillingAddress;
    private Boolean collectShippingAddress;
    private String merchantReturnUrl;
    private String notifyUrl;
}
