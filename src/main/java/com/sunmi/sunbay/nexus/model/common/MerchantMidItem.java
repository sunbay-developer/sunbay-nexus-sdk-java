package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MID information assigned by a payment processor
 *
 * @since 2026-08-31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantMidItem {

    /**
     * Payment channel code identifying the processor this MID belongs to
     */
    private String channelCode;

    /**
     * Payment channel display name (for presentation only)
     */
    private String channelName;

    /**
     * Merchant Identification Number (MID) assigned by the payment processor.
     * Note: this differs from the SUNBAY platform merchantId
     */
    private String mid;
}
