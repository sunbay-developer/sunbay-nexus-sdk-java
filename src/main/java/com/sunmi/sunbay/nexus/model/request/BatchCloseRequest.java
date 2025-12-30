package com.sunmi.sunbay.nexus.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * Batch close request
 *
 * @since 2025-12-10
 */
@Data
@Builder
public class BatchCloseRequest {

    /**
     * Application ID
     */
    private String appId;

    /**
     * Merchant ID
     */
    private String merchantId;

    /**
     * Batch close request unique identifier. Unique ID to identify this batch close request, used as API idempotency control field, can be used later to query batch close results
     */
    private String transactionRequestId;

    /**
     * Terminal serial number. SUNBAY provided financial POS device serial number for reading bank cards and processing PIN security operations
     */
    private String terminalSn;

    /**
     * Payment channel code
     */
    private String channelCode;

    /**
     * Batch close description
     */
    private String description;
}
