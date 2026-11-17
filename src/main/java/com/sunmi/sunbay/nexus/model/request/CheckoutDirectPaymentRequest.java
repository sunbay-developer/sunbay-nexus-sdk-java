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

    /**
     * Required
     */
    private String appId;
    /**
     * Required
     */
    private String merchantId;
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
    private String description;
    /**
     * Required
     */
    private CheckoutAmount amount;
    /**
     * Optional
     */
    private List<CheckoutLineItem> productList;
    /**
     * e.g. GOOGLE_PAY, APPLE_PAY (required)
     */
    private String paymentMethod;
    /**
     * Wallet encrypted token JSON string; needed when paymentMethod is GOOGLE_PAY or APPLE_PAY (optional)
     */
    private String cardEncryptedData;
    /**
     * Optional
     */
    private String customerEmail;
    /**
     * Optional
     */
    private String customerName;
    /**
     * Optional
     */
    private CheckoutAddress billingAddress;
    /**
     * Optional
     */
    private CheckoutAddress shippingAddress;
    /**
     * Optional
     */
    private String notifyUrl;
    /**
     * Optional
     */
    private String merchantReturnUrl;
}
