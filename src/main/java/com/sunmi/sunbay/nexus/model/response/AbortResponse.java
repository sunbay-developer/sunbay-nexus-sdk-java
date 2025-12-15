package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;

/**
 * Abort response
 *
 * @since 2025-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AbortResponse extends BaseResponse {

    /**
     * Aborted transaction's SUNBAY Nexus transaction ID
     */
    private String originalTransactionId;

    /**
     * Aborted transaction's request ID (only returned when provided in request)
     */
    private String originalTransactionRequestId;
}
