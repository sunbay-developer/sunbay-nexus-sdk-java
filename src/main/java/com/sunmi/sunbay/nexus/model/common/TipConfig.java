package com.sunmi.sunbay.nexus.model.common;

import lombok.Data;

import java.util.List;

/**
 * Tip configuration
 *
 * @since 2025-12-15
 */
@Data
public class TipConfig {

    private Boolean onScreenTip;

    private String tipMode;

    private Boolean tipWithTax;

    private List<TipSuggestions> suggestions;

    /**
     * Whether to enable on-screen tip input
     */
    public void setOnScreenTip(Boolean onScreenTip) {
        this.onScreenTip = onScreenTip;
    }

    /**
     * Tip mode: ON_SALE (tip during sale) / AFTER_SALE (tip after sale)
     */
    public void setTipMode(String tipMode) {
        this.tipMode = tipMode;
    }

    /**
     * Whether tip amount includes tax
     */
    public void setTipWithTax(Boolean tipWithTax) {
        this.tipWithTax = tipWithTax;
    }

    /**
     * Tip suggestions list, max 3 items.
     * Each item's names and values arrays must also be max 3 elements.
     */
    public void setSuggestions(List<TipSuggestions> suggestions) {
        if (suggestions != null && suggestions.size() > 3) {
            throw new IllegalArgumentException("Tip suggestions support up to 3 items.");
        }
        this.suggestions = suggestions;
    }
}
