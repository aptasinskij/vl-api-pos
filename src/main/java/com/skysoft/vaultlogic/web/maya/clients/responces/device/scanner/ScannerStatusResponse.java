package com.skysoft.vaultlogic.web.maya.clients.responces.device.scanner;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.web.maya.clients.responces.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ScannerStatusResponse extends BaseResponse {

    @JsonProperty("scanner_enabled")
    private String scannerEnabled;

    private String qr;

    public String getScannerEnabled() {
        return scannerEnabled;
    }

    public void setScannerEnabled(String scannerEnabled) {
        this.scannerEnabled = scannerEnabled;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
}