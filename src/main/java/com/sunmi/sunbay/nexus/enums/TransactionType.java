package com.sunmi.sunbay.nexus.enums;

/**
 * Transaction type enum
 *
 * @since 2025-12-10
 */
public enum TransactionType {

    /**
     * Sale transaction
     */
    SALE,

    /**
     * Authorization (pre-auth)
     */
    AUTH,

    /**
     * Forced authorization
     */
    FORCED_AUTH,

    /**
     * Incremental authorization
     */
    INCREMENTAL,

    /**
     * Post authorization (pre-auth completion)
     */
    POST_AUTH,

    /**
     * Refund
     */
    REFUND,

    /**
     * Void
     */
    VOID
}
