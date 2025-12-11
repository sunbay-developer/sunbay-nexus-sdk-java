package com.sunmi.sunbay.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Payment category enum
 *
 * @since 2025-12-10
 */
public enum PaymentCategory {

    /**
     * Card payment
     */
    CARD("CARD"),

    /**
     * Credit card network
     */
    CARD_CREDIT("CARD-CREDIT"),

    /**
     * Debit card network
     */
    CARD_DEBIT("CARD-DEBIT"),

    /**
     * QR code merchant presented mode
     */
    QR_MPM("QR-MPM"),

    /**
     * QR code customer presented mode
     */
    QR_CPM("QR-CPM");

    private final String value;

    PaymentCategory(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
