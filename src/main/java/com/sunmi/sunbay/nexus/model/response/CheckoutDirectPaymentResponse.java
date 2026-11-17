package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.Amount;
import com.sunmi.sunbay.nexus.model.common.BaseResponse;
import com.sunmi.sunbay.nexus.model.common.CheckoutAction;

/**
 * Response for online direct payment ({@code POST /v1/checkout/sale}).
 *
 * @since 2026-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CheckoutDirectPaymentResponse extends BaseResponse {

    /**
     * Next step the customer must take (e.g. 3DS redirect); null when none is required
     */
    private CheckoutAction action;

    private Amount amount;
    private String authCode;
    private String authenticationMethod;
    private String cardNetworkType;
    private String completeTime;
    private String createTime;
    private String description;
    private String entryMode;
    private String maskedPan;
    private String paymentMethodId;
    private String referenceOrderId;
    private String rrn;
    private String stan;
    private String subPaymentMethodId;
    private String transactionId;
    private String transactionRequestId;
    private String transactionResultCode;
    private String transactionResultMsg;
    private String transactionStatus;
    private String transactionType;
    private String voucherNo;
}
