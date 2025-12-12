package com.sunmi.sunbay.nexus.enums;

/**
 * Transaction status enum
 *
 * @since 2025-12-10
 */
public enum TransactionStatus {

    /**
     * Initial state
     */
    INITIAL("I", "INITIAL"),

    /**
     * Transaction processing. Channel called but no result obtained, or unexpected
     * exception returned.
     */
    PROCESSING("P", "PROCESSING"),

    /**
     * Transaction successful
     */
    SUCCESS("S", "SUCCESS"),

    /**
     * Transaction failed
     */
    FAIL("F", "FAIL"),

    /**
     * Transaction closed
     */
    CLOSED("C", "CLOSED");

    private final String code;
    private final String desc;

    TransactionStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
