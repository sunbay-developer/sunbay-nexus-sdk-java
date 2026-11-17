package com.sunmi.sunbay.nexus.model.common;

import lombok.Data;

/**
 * Signature configuration. Controls signature collection behavior after transaction completion.
 * When this field is not provided, the SUNBAY platform signature configuration is used by default.
 * This replaces the deprecated signatureEntryLocation field.
 *
 * @since 2026-08-06
 */
@Data
public class SignatureConfig {

    /**
     * Whether to use SUNBAY platform signature configuration.
     * When true, platform configuration is used and entryLocation/threshold are ignored.
     * When false, the configuration provided in this request is used.
     * Default: true (optional)
     */
    private Boolean useHostConfig;

    /**
     * Signature entry location. Used when useHostConfig is false.
     * Possible values: ON_SCREEN (terminal screen signature), ON_RECEIPT (receipt signature), NONE (no signature) (optional)
     */
    private String entryLocation;

    /**
     * Signature threshold amount in smallest currency unit.
     * When transaction amount >= this value, signature is collected; below this value, signature is skipped.
     * Only effective when useHostConfig is false and entryLocation is not NONE.
     * When not provided, signature is collected for all amounts. (optional)
     */
    private Integer threshold;
}
