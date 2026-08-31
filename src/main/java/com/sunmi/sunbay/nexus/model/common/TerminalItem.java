package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Terminal information bound to a merchant
 *
 * @since 2026-08-31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TerminalItem {

    /**
     * Terminal serial number
     */
    private String sn;

    /**
     * Device vendor / manufacturer
     */
    private String vendor;

    /**
     * Device model
     */
    private String model;

    /**
     * Time the terminal was bound to the merchant (ISO 8601)
     */
    private String createTime;

    /**
     * TIDs assigned to this terminal by each payment channel (Processor).
     * A terminal may be onboarded to multiple processors, and each processor assigns its own TID.
     * Returns an empty array if no payment channel has been enabled for the terminal yet.
     */
    private List<TerminalTidItem> tidList;
}
