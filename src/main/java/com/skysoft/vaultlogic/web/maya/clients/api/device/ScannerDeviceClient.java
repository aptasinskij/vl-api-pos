package com.skysoft.vaultlogic.web.maya.clients.api.device;

import com.skysoft.vaultlogic.web.maya.clients.responce.device.scanner.GetScannerStatusResponse;
import org.springframework.http.ResponseEntity;

public interface ScannerDeviceClient {

    ResponseEntity<GetScannerStatusResponse> getScannerDeviceStatus(String xToken);

}
