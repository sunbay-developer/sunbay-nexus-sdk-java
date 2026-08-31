package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;
import com.sunmi.sunbay.nexus.model.common.BatchCloseListItem;

import java.util.List;

/**
 * Batch close list response
 *
 * @since 2026-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BatchCloseListResponse extends BaseResponse {

    /**
     * List of closed batch records
     */
    private List<BatchCloseListItem> batchCloseList;
}
