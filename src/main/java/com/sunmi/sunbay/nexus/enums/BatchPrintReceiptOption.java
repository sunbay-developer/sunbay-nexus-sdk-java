package com.sunmi.sunbay.nexus.enums;

/**
 * Batch close print receipt option enum.
 * Controls the report content printed by the terminal after batch close.
 *
 * @since 2026-08-06
 */
public enum BatchPrintReceiptOption {

    /**
     * Print batch summary only
     */
    TOTAL,

    /**
     * Print batch detail only (transaction by transaction)
     */
    DETAIL,

    /**
     * Print both summary and detail
     */
    BOTH,

    /**
     * Do not print batch report
     */
    NONE,

    /**
     * Use SUNBAY platform configuration
     */
    AUTO
}
