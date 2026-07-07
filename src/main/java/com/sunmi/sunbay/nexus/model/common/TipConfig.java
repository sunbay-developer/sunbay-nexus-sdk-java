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

    /**
     * Whether to enable on-screen tip input
     */
    private Boolean onScreenTip;

    /**
     * Tip mode: ON_SALE (tip during sale) / AFTER_SALE (tip after sale)
     */
    private String tipMode;

    /**
     * Whether tip amount includes tax
     */
    private Boolean tipWithTax;

    /**
     * Tip suggestions, max 3
     */
    private List<TipSuggestions> suggestions;
}
