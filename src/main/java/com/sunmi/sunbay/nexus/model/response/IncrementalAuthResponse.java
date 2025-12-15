package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;

/**
 * Incremental authorization response
 *
 * @since 2025-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IncrementalAuthResponse extends BaseResponse {

    /**
     * SUNBAY Nexus transaction ID for this incremental authorization transaction, used for subsequent queries and notifications
     */
    private String transactionId;

    /**
     * Transaction request ID for this incremental authorization, returned as-is from request
     */
    private String transactionRequestId;

    /**
     * Original authorization transaction ID, returned as-is from request (only returned when provided in request)
     */
    private String originalTransactionId;

    /**
     * Original authorization transaction request ID, returned as-is from request (only returned when provided in request)
     */
    private String originalTransactionRequestId;
}
