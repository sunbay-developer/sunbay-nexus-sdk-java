package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;

/**
 * Void response
 *
 * @since 2025-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VoidResponse extends BaseResponse {

    /**
     * SUNBAY Nexus transaction ID for this void transaction, used for subsequent queries and notifications
     */
    private String transactionId;

    /**
     * Transaction request ID for this void, returned as-is from request
     */
    private String transactionRequestId;

    /**
     * Original transaction ID
     */
    private String originalTransactionId;

    /**
     * Original transaction request ID
     */
    private String originalTransactionRequestId;
}
