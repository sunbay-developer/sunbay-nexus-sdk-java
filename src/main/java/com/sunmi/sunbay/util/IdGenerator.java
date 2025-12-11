package com.sunmi.sunbay.util;

import java.util.UUID;

/**
 * ID generator utility
 *
 * @since 2025-12-10
 */
public class IdGenerator {

    private IdGenerator() {
    }

    /**
     * Generate UUID
     *
     * @return UUID string
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generate request ID
     *
     * @return request ID
     */
    public static String generateRequestId() {
        return generateUUID();
    }
}
