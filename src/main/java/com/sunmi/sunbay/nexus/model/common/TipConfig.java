package com.sunmi.sunbay.nexus.model.common;

import lombok.Data;

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

    private TipSuggestions suggestions;

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
     * Tip suggestions configuration.
     * The names and values arrays within must be max 3 elements each.
     */
    public void setSuggestions(TipSuggestions suggestions) {
        this.suggestions = suggestions;
    }
}
