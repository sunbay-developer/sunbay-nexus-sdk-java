package com.sunmi.sunbay.enums;

/**
 * Authentication method enum
 *
 * @since 2025-12-10
 */
public enum AuthenticationMethod {

    /**
     * Not authenticated
     */
    NOT_AUTHENTICATED,

    /**
     * PIN authentication
     */
    PIN,

    /**
     * Offline PIN
     */
    OFFLINE_PIN,

    /**
     * Bypass authentication
     */
    BY_PASS,

    /**
     * Signature authentication
     */
    SIGNATURE
}
