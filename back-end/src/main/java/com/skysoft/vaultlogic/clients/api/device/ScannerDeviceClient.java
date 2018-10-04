package com.skysoft.vaultlogic.clients.api.device;

import com.skysoft.vaultlogic.clients.responseModels.device.scanner.ScannerStatusInfo;

public interface ScannerDeviceClient {

    ScannerStatusInfo getScannerDeviceStatus(String xToken);

}
