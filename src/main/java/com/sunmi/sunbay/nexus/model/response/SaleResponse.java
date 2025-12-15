package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;

/**
 * Sale transaction response
 *
 * @since 2025-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleResponse extends BaseResponse {

    /**
     * SUNBAY Nexus transaction ID for this sale transaction, used for subsequent queries and notifications
     */
    private String transactionId;

    /**
     * Reference order ID, returned as-is from request
     */
    private String referenceOrderId;

    /**
     * Transaction request ID, returned as-is from request
     */
    private String transactionRequestId;
}
