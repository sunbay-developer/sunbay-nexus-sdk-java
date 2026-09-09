package com.sunmi.sunbay.nexus.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sunmi.sunbay.nexus.constant.ApiConstants;
import com.sunmi.sunbay.nexus.exception.SunbayBusinessException;

/**
 * JSON utility class
 *
 * @since 2025-12-10
 */
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // Ignore unknown properties
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // Ignore null values
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // Disable writing dates as timestamps
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    private JsonUtil() {
    }

    /**
     * Convert object to JSON string
     *
     * @param obj object
     * @return JSON string
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SunbayBusinessException(
                    ApiConstants.ERROR_CODE_PARAMETER_ERROR,
                    "Failed to serialize object to JSON",
                    null,
                    e);
        }
    }

    /**
     * Parse JSON string to object
     *
     * @param json  JSON string
     * @param clazz target class
     * @param <T>   type
     * @return object
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new SunbayBusinessException(
                    ApiConstants.ERROR_CODE_PARAMETER_ERROR,
                    "Failed to parse JSON to object",
                    null,
                    e);
        }
    }

    /**
     * Parse a JSON string into a tree model. Internal SDK use for envelope
     * inspection where a value POJO is not the right target.
     *
     * @param json JSON string
     * @return root {@link JsonNode}
     * @throws JsonProcessingException if the input is not valid JSON
     * @since 2026-09-09
     */
    public static JsonNode readTree(String json) throws JsonProcessingException {
        return OBJECT_MAPPER.readTree(json);
    }

    /**
     * Bind a Jackson tree node into a value object.
     *
     * @param node  source tree node
     * @param clazz target class
     * @param <T>   type
     * @return deserialized object
     * @throws JsonProcessingException if the node does not match the target type
     * @since 2026-09-09
     */
    public static <T> T treeToValue(TreeNode node, Class<T> clazz) throws JsonProcessingException {
        return OBJECT_MAPPER.treeToValue(node, clazz);
    }

    /**
     * Build a fresh instance of the given type with all fields left at their
     * defaults. Equivalent to deserializing an empty JSON object.
     *
     * @param clazz target class (must have a no-arg constructor / be Jackson-instantiable)
     * @param <T>   type
     * @return a new instance
     * @throws JsonProcessingException if {@code clazz} cannot be instantiated by Jackson
     * @since 2026-09-09
     */
    public static <T> T emptyValue(Class<T> clazz) throws JsonProcessingException {
        return OBJECT_MAPPER.treeToValue(OBJECT_MAPPER.createObjectNode(), clazz);
    }

    /**
     * Internal accessor for the shared {@link ObjectMapper} instance. Kept
     * package-private on purpose: callers must not reconfigure the shared
     * mapper at runtime, since Jackson's configuration is only thread-safe
     * once the mapper is done being configured.
     */
    private static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
}
