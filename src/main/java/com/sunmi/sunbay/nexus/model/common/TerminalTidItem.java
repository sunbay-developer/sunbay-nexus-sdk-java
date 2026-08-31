package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TID information assigned by a payment processor to a terminal
 *
 * @since 2026-08-31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TerminalTidItem {

    /**
     * Payment channel code identifying the processor this TID belongs to
     */
    private String channelCode;

    /**
     * Payment channel display name (for presentation only)
     */
    private String channelName;

    /**
     * Terminal Identification Number (TID) assigned by the payment processor.
     * Note: this differs from the terminal serial number (sn)
     */
    private String tid;
}
