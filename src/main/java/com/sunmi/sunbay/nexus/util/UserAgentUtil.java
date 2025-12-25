package com.sunmi.sunbay.nexus.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * User-Agent utility for generating SDK User-Agent string
 * <p>
 * Format: SunbayNexusSDK-{Language}/{SDKVersion} {Language}/{LanguageVersion} {OS}/{OSVersion}
 * Example: SunbayNexusSDK-Java/1.0.3 Java/1.8.0_291 Darwin/25.1.0
 * </p>
 *
 * @since 2025-12-22
 */
public class UserAgentUtil {

    private static final String SDK_NAME = "SunbayNexusSDK-Java";
    private static final String LANGUAGE = "Java";
    private static final String UNKNOWN_VERSION = "unknown";
    private static final String VERSION_PROPERTIES = "/com/sunmi/sunbay/nexus/version.properties";
    
    private static volatile String cachedUserAgent;

    private UserAgentUtil() {
    }

    /**
     * Generate User-Agent string
     * <p>
     * Format: SunbayNexusSDK-Java/{SDKVersion} Java/{JavaVersion} {OS}/{OSVersion}
     * </p>
     *
     * @return User-Agent string
     */
    public static String getUserAgent() {
        if (cachedUserAgent == null) {
            synchronized (UserAgentUtil.class) {
                if (cachedUserAgent == null) {
                    cachedUserAgent = buildUserAgent();
                }
            }
        }
        return cachedUserAgent;
    }

    /**
     * Build User-Agent string dynamically
     *
     * @return User-Agent string
     */
    private static String buildUserAgent() {
        String sdkVersion = getSdkVersion();
        String javaVersion = getJavaVersion();
        String osName = normalizeOsName(System.getProperty("os.name", UNKNOWN_VERSION));
        String osVersion = System.getProperty("os.version", UNKNOWN_VERSION);

        return String.format("%s/%s %s/%s %s/%s",
                SDK_NAME, sdkVersion,
                LANGUAGE, javaVersion,
                osName, osVersion);
    }

    /**
     * Get SDK version from package metadata or properties file
     * <p>
     * First tries Package.getImplementationVersion() from MANIFEST.MF (for packaged JAR).
     * Falls back to reading version.properties file (for IDE/local development).
     * </p>
     *
     * @return SDK version, or "unknown" if not available
     */
    private static String getSdkVersion() {
        // Try to get version from MANIFEST.MF (works when running from JAR)
        try {
            Package pkg = UserAgentUtil.class.getPackage();
            if (pkg != null) {
                String version = pkg.getImplementationVersion();
                if (version != null && !version.isEmpty()) {
                    return version;
                }
            }
        } catch (Exception e) {
            // Ignore, fall through to properties file
        }
        
        // Fallback: read from version.properties (works in IDE/local development)
        try {
            InputStream is = UserAgentUtil.class.getResourceAsStream(VERSION_PROPERTIES);
            if (is != null) {
                Properties props = new Properties();
                props.load(is);
                String version = props.getProperty("version");
                if (version != null && !version.isEmpty() && !version.startsWith("${")) {
                    return version;
                }
            }
        } catch (Exception e) {
            // Ignore
        }
        
        return UNKNOWN_VERSION;
    }

    /**
     * Get Java version
     *
     * @return Java version
     */
    private static String getJavaVersion() {
        return System.getProperty("java.version", UNKNOWN_VERSION);
    }

    /**
     * Normalize OS name to match specification format
     * Examples:
     * - "Mac OS X" -> "Darwin"
     * - "Windows 10" -> "Windows"
     * - "Linux" -> "Linux"
     *
     * @param osName original OS name
     * @return normalized OS name
     */
    private static String normalizeOsName(String osName) {
        if (osName == null || osName.isEmpty()) {
            return UNKNOWN_VERSION;
        }
        
        String lowerOsName = osName.toLowerCase();
        if (lowerOsName.contains("mac") || lowerOsName.contains("darwin")) {
            return "Darwin";
        } else if (lowerOsName.contains("windows")) {
            return "Windows";
        } else if (lowerOsName.contains("linux")) {
            return "Linux";
        } else if (lowerOsName.contains("unix")) {
            return "Unix";
        }
        
        // Return original if no match
        return osName;
    }
}

