package com.sunmi.sunbay.nexus.enums;

/**
 * Transaction batch settlement status enum.
 *
 * @since 2026-06-29
 */
public enum TransactionBatchStatus {

    /**
     * No batch settlement needed
     */
    NB,

    /**
     * Waiting for batch close
     */
    UB,

    /**
     * Batch closed
     */
    BC
}
