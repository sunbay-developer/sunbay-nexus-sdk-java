package com.sunmi.sunbay.nexus.enums;

/**
 * Related transaction status enum.
 * Indicates the lifecycle change of the current transaction due to subsequent transactions.
 *
 * @since 2026-06-29
 */
public enum RelatedTransactionStatus {

    /**
     * Transaction has been voided
     */
    VOIDED,

    /**
     * Transaction has incremental authorization
     */
    INCREMENTAL,

    /**
     * Transaction has been fully refunded
     */
    REFUNDED,

    /**
     * Transaction has been captured (post-auth)
     */
    CAPTURE,

    /**
     * Transaction has been partially refunded
     */
    PART_REFUNDED
}
