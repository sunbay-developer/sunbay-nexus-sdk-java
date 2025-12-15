package com.sunmi.sunbay.nexus.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * Query request
 *
 * @since 2025-12-10
 */
@Data
@Builder
public class QueryRequest {

    /**
     * Application ID
     */
    private String appId;

    /**
     * Merchant ID
     */
    private String merchantId;

    /**
     * SUNBAY Nexus transaction ID. Either transactionId or transactionRequestId is required. If both are provided, transactionId takes priority
     */
    private String transactionId;

    /**
     * Transaction request ID. Either transactionId or transactionRequestId is required. If both are provided, transactionId takes priority
     */
    private String transactionRequestId;
}
