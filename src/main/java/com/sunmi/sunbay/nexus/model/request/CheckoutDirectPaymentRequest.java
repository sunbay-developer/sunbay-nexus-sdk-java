package com.sunmi.sunbay.nexus.model.request;

import java.util.List;

import lombok.Builder;
import lombok.Data;

import com.sunmi.sunbay.nexus.model.common.CheckoutAddress;
import com.sunmi.sunbay.nexus.model.common.CheckoutAmount;
import com.sunmi.sunbay.nexus.model.common.CheckoutLineItem;

/**
 * Request for {@code POST /v1/checkout/sale} (online direct payment, e.g. Google Pay / Apple Pay).
 *
 * @see <a href="https://docs.sunbay.dev/en/refspec/online/direct-payment">Direct payment</a>
 * @since 2026-02-02
 */
@Data
@Builder
public class CheckoutDirectPaymentRequest {

    private String appId;
    private String merchantId;
    private String transactionRequestId;
    private String referenceOrderId;
    private String description;
    private CheckoutAmount amount;
    private List<CheckoutLineItem> productList;
    /**
     * e.g. GOOGLE_PAY, APPLE_PAY
     */
    private String paymentMethod;
    /**
     * Wallet encrypted token JSON string; required when paymentMethod is GOOGLE_PAY or APPLE_PAY
     */
    private String cardEncryptedData;
    private String customerEmail;
    private String customerName;
    private CheckoutAddress billingAddress;
    private CheckoutAddress shippingAddress;
    private String notifyUrl;
    private String merchantReturnUrl;
}
