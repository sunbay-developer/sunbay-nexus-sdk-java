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
    N,

    /**
     * Waiting for batch close
     */
    U,

    /**
     * Batch closed
     */
    C
}
