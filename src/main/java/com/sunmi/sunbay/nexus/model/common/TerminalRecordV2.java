package com.sunmi.sunbay.nexus.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Terminal record returned by the v2 merchant terminals query (GET /v2/merchant/terminal/query).
 * Unlike {@link TerminalItem} (v1), the serial number field is {@code terminalSn} instead of {@code sn}.
 *
 * @author andy.li, kiro
 * @since 2026-11-17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TerminalRecordV2 {

    /**
     * Terminal ID
     */
    private String terminalId;

    /**
     * Terminal serial number
     */
    private String terminalSn;

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
     */
    private List<TerminalTidItem> tidList;
}
