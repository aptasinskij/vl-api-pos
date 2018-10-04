package com.skysoft.vaultlogic.clients.responseModels.device.scanner;

import com.skysoft.vaultlogic.clients.responseModels.BaseInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ScannerStatusInfo extends BaseInfo {

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