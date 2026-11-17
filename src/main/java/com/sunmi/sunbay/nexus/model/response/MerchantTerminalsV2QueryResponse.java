package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;
import com.sunmi.sunbay.nexus.model.common.TerminalRecordV2;

import java.util.List;

/**
 * Merchant terminals query response (v2)
 *
 * @since 2026-10-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MerchantTerminalsV2QueryResponse extends BaseResponse {

    /**
     * Opaque pagination token for retrieving the next page.
     * Only present when more terminals are available; absence indicates the end of the list.
     */
    private String nextToken;

    /**
     * Terminals on the current page
     */
    private List<TerminalRecordV2> records;
}
