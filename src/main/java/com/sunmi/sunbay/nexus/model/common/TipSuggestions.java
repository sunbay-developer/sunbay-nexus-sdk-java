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
     * Display names for tip options.
     * Must match values in length and order if not empty. Max 3.
     */
    private List<String> names;

    /**
     * Fee mode: RATE (percentage) / AMOUNT (fixed amount)
     */
    private String feeMode;

    /**
     * Suggestion values (percentage or fixed amount depending on feeMode). Max 3.
     * Must match names in length and order if names is not empty.
     */
    private List<Double> values;
}
