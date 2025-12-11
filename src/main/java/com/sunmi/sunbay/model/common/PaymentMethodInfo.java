package com.sunmi.sunbay.model.common;

import com.sunmi.sunbay.enums.PaymentCategory;

/**
 * Payment method information
 *
 * @since 2025-12-10
 */
public class PaymentMethodInfo {

    /**
     * Payment category
     */
    private String category;

    /**
     * Payment method ID
     */
    private String id;

    /**
     * Create card payment method
     *
     * @param cardType card type (e.g., VISA, MASTERCARD)
     * @return PaymentMethodInfo instance
     */
    public static PaymentMethodInfo card(String cardType) {
        PaymentMethodInfo info = new PaymentMethodInfo();
        info.setCategory(PaymentCategory.CARD.getValue());
        info.setId(cardType);
        return info;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
