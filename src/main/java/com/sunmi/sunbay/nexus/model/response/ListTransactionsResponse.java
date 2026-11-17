package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;
import com.sunmi.sunbay.nexus.model.common.TransactionRecord;

import java.util.List;

/**
 * List transactions response
 *
 * @since 2026-10-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ListTransactionsResponse extends BaseResponse {

    /**
     * Transaction records on the current page
     */
    private List<TransactionRecord> records;

    /**
     * Opaque pagination token for retrieving the next page.
     * Only present when more records are available; absence indicates the end of the list.
     */
    private String nextToken;
}
