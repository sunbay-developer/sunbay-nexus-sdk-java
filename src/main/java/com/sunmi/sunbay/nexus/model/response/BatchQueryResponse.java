package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;
import com.sunmi.sunbay.nexus.model.common.BatchQueryItem;

import java.util.List;

/**
 * Batch query response
 *
 * @since 2025-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BatchQueryResponse extends BaseResponse {

    /**
     * Batch list, statistics grouped by channel code and price currency
     */
    private List<BatchQueryItem> batchList;
}
