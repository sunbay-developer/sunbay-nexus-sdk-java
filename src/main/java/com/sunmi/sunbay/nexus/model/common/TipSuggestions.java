package com.sunmi.sunbay.nexus.model.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Tip suggestions configuration
 *
 * @since 2025-12-15
 */
@Data
public class TipSuggestions {

    private List<String> names;

    private String feeMode;

    private List<Double> values;

    /**
     * Display names for tip options.
     * Must match values in length and order if not empty. Max 3.
     */
    public void setNames(List<String> names) {
        validateSize(names, "names");
        validateMatch(names, values, "names");
        this.names = copy(names);
    }

    /**
     * Fee mode: RATE (percentage) / AMOUNT (fixed amount)
     */
    public void setFeeMode(String feeMode) {
        this.feeMode = feeMode;
    }

    /**
     * Suggestion values (percentage or fixed amount depending on feeMode). Max 3.
     * Must match names in length and order if names is not empty.
     */
    public void setValues(List<Double> values) {
        validateSize(values, "values");
        validateMatch(names, values, "values");
        this.values = copy(values);
    }

    private static void validateSize(List<?> value, String fieldName) {
        if (value != null && value.size() > 3) {
            throw new IllegalArgumentException("Tip suggestions " + fieldName + " support up to 3 items.");
        }
    }

    private static void validateMatch(List<String> names, List<Double> values, String fieldName) {
        if (names != null && !names.isEmpty() && values != null && names.size() != values.size()) {
            throw new IllegalArgumentException("Tip suggestion names must match values in length for " + fieldName + ".");
        }
    }

    private static <T> List<T> copy(List<T> value) {
        return value == null ? null : new ArrayList<>(value);
    }
}
