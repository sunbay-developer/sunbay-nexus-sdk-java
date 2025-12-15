package com.sunmi.sunbay.nexus.model.common;

import lombok.Data;

import com.sunmi.sunbay.nexus.constant.ApiConstants;

/**
 * Base response
 *
 * @since 2025-12-10
 */
@Data
public class BaseResponse {

    /**
     * Response code, 0 means success
     */
    private String code;

    /**
     * Response message
     */
    private String msg;

    /**
     * Trace ID for troubleshooting
     */
    private String traceId;

    /**
     * Check if response is successful
     *
     * @return true if success
     */
    public boolean isSuccess() {
        return ApiConstants.RESPONSE_SUCCESS_CODE.equals(code);
    }}
