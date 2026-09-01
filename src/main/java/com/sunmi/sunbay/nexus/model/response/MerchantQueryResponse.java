package com.sunmi.sunbay.nexus.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sunmi.sunbay.nexus.model.common.BaseResponse;
import com.sunmi.sunbay.nexus.model.common.MerchantMidItem;

import java.util.List;

/**
 * Merchant query response
 *
 * @since 2026-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MerchantQueryResponse extends BaseResponse {

    /**
     * Merchant ID
     */
    private String merchantId;

    /**
     * "Doing Business As" name — the merchant's public/trading name shown to customers
     */
    private String dbaName;

    /**
     * Merchant Category Code (ISO 18245)
     */
    private String mcc;

    /**
     * ISO 3166-1 alpha-3 country code
     */
    private String country;

    /**
     * State or province name
     */
    private String stateName;

    /**
     * City name
     */
    private String cityName;

    /**
     * Street address
     */
    private String street;

    /**
     * Full detailed address (street number, suite, etc.)
     */
    private String detailAddress;

    /**
     * Postal / ZIP code
     */
    private String zipCode;

    /**
     * Merchant status. Y: active, N: inactive
     */
    private String status;

    /**
     * Merchant creation time (ISO 8601)
     */
    private String createTime;

    /**
     * MIDs assigned to this merchant by each payment channel (Processor).
     * A merchant may be onboarded to multiple processors, and each processor assigns its own MID.
     * Returns an empty array if no payment channel has been enabled yet.
     */
    private List<MerchantMidItem> midList;
}
