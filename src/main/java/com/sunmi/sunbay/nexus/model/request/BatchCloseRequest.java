package com.sunmi.sunbay.nexus.model.request;

/**
 * Batch close request
 *
 * @since 2025-12-10
 */
public class BatchCloseRequest {

    private String appId;
    private String merchantId;
    private String terminalSn;
    private Boolean enablePushToTerminal;
    private String description;
    private String attach;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getTerminalSn() {
        return terminalSn;
    }

    public void setTerminalSn(String terminalSn) {
        this.terminalSn = terminalSn;
    }

    public Boolean getEnablePushToTerminal() {
        return enablePushToTerminal;
    }

    public void setEnablePushToTerminal(Boolean enablePushToTerminal) {
        this.enablePushToTerminal = enablePushToTerminal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }
}
