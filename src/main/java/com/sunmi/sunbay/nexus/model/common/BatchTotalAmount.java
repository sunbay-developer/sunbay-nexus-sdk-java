package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Batch total amount information
 *
 * @since 2025-12-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchTotalAmount {

    /**
     * Price currency (ISO 4217)
     */
    private String priceCurrency;

    /**
     * Total amount
     */
    private Integer amount;
}

