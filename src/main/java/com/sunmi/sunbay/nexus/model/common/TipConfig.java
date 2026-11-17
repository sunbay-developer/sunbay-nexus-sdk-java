package com.sunmi.sunbay.nexus.model.common;

import lombok.Data;

/**
 * Tip configuration
 *
 * @since 2025-12-15
 */
@Data
public class TipConfig {

    /**
     * Whether to use SUNBAY platform tip configuration.
     * When true, Tapro will fully follow the platform's tip configuration for UI display and business logic,
     * and all other tipConfig parameters (onScreenTip, tipMode, tipWithTax, suggestions) will be ignored.
     * Default: false (optional)
     */
    private Boolean useHostConfig;

    /**
     * Optional
     */
    private Boolean onScreenTip;

    /**
     * Optional
     */
    private String tipMode;

    /**
     * Optional
     */
    private Boolean tipWithTax;

    /**
     * Optional
     */
    private TipSuggestions suggestions;

    /**
     * Whether to allow tip adjustment after the transaction is completed.
     * When true, the tip amount can be adjusted via the Tip Adjust API after the transaction succeeds. (optional)
     */
    private Boolean allowTipAdjustment;

    /**
     * Whether to use SUNBAY platform tip configuration.
     * When true, all other tipConfig parameters are ignored.
     */
    public void setUseHostConfig(Boolean useHostConfig) {
        this.useHostConfig = useHostConfig;
    }

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

    /**
     * Whether to allow tip adjustment after the transaction is completed.
     */
    public void setAllowTipAdjustment(Boolean allowTipAdjustment) {
        this.allowTipAdjustment = allowTipAdjustment;
    }
}
