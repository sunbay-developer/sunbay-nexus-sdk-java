package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;
import com.sunmi.sunbay.nexus.model.common.TerminalItem;

import java.util.List;

/**
 * Merchant terminals query response
 *
 * @since 2026-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MerchantTerminalsQueryResponse extends BaseResponse {

    /**
     * Merchant ID (echoed from the request)
     */
    private String merchantId;

    /**
     * Opaque pagination token for retrieving the next page.
     * Only present when more terminals are available; absence indicates the end of the list.
     */
    private String nextToken;

    /**
     * Terminals on the current page
     */
    private List<TerminalItem> terminals;
}
