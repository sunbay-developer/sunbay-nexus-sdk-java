package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Payment method information
 *
 * @since 2025-12-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodInfo {

    /**
     * Payment category: CARD (bank card)/CARD-CREDIT (credit card network)/CARD-DEBIT (debit card network)/QR-MPM (QR code merchant present mode)/QR-CPM (QR code customer present mode)
     */
    private String category;

    /**
     * Specific payment method: WECHAT (WeChat)/ALIPAY (Alipay) etc. For card payments, usually only category needs to be specified
     */
    private String id;
}
