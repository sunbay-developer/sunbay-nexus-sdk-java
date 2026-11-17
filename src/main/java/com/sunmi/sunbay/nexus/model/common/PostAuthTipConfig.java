package com.sunmi.sunbay.nexus.model.common;

import lombok.Data;

/**
 * Tip configuration for the post-authorization request.
 * Unlike {@link TipConfig} (used by sale), it does not carry allowTipAdjustment.
 *
 * @author andy.li, kiro
 * @since 2026-11-17
 */
@Data
public class PostAuthTipConfig {

    /**
     * Whether to use SUNBAY platform tip configuration.
     * When true, all other tipConfig parameters are ignored. (optional)
     */
    private Boolean useHostConfig;

    /**
     * Whether to enable on-screen tip input (optional)
     */
    private Boolean onScreenTip;

    /**
     * Tip mode: ON_SALE (tip during sale) / AFTER_SALE (tip after sale) (optional)
     */
    private String tipMode;

    /**
     * Whether tip amount includes tax (optional)
     */
    private Boolean tipWithTax;

    /**
     * Tip suggestions configuration.
     * The names and values arrays within must be max 3 elements each. (optional)
     */
    private TipSuggestions suggestions;
}
