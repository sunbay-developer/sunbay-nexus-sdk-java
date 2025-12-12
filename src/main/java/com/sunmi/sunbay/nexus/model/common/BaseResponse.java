package com.sunmi.sunbay.nexus.model.common;

import com.sunmi.sunbay.nexus.constant.ApiConstants;

/**
 * Base response
 *
 * @since 2025-12-10
 */
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
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
}
