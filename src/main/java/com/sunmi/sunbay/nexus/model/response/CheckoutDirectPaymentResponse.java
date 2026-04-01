package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;

/**
 * Response for online direct payment ({@code POST /v1/checkout/sale}).
 *
 * @since 2026-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CheckoutDirectPaymentResponse extends BaseResponse {

    private String transactionId;
    private String referenceOrderId;
    private String transactionRequestId;
}
