package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;

/**
 * Response for expire checkout session.
 *
 * @since 2026-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ExpireCheckoutSessionResponse extends BaseResponse {

    /**
     * Echo of the session ID from the request
     */
    private String sessionId;

    /**
     * Session status after expiration, always "EXPIRED"
     */
    private String sessionStatus;

    /**
     * SUNBAY transaction ID associated with the session (if generated during session creation)
     */
    private String transactionId;

    /**
     * Transaction request ID used when creating the session
     */
    private String transactionRequestId;

    /**
     * Session expiration time in ISO 8601 format (e.g. "2023-11-19T10:30:00+08:00")
     */
    private String expiredAt;
}
