package com.sunmi.sunbay.nexus.model.common;

import lombok.Data;

import java.util.List;

/**
 * Tip suggestions configuration
 *
 * @since 2025-12-15
 */
@Data
public class TipSuggestions {

    /**
     * Display name for tip option
     */
    private String name;

    /**
     * Fee mode: RATE (percentage) / AMOUNT (fixed amount)
     */
    private String feeMode;

    /**
     * Suggestion values (percentage or fixed amount depending on feeMode), max 3
     */
    private List<Double> values;
}
