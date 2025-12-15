package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;

/**
 * Tip adjust response
 *
 * @since 2025-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TipAdjustResponse extends BaseResponse {

    /**
     * Original transaction's SUNBAY Nexus transaction ID (only returned when provided in request)
     */
    private String originalTransactionId;

    /**
     * Original transaction's request ID (only returned when provided in request)
     */
    private String originalTransactionRequestId;

    /**
     * Adjusted tip amount, in basic currency unit, returned as-is from request
     */
    private Double tipAmount;
}
