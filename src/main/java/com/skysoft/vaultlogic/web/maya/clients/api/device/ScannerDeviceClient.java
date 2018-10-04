package com.skysoft.vaultlogic.web.maya.clients.api.device;

import com.skysoft.vaultlogic.web.maya.clients.responseModels.device.scanner.ScannerStatusInfo;

public interface ScannerDeviceClient {

    ScannerStatusInfo getScannerDeviceStatus(String xToken);

}
