package com.skysoft.vaultlogic.web.maya.clients.api.device;

import com.skysoft.vaultlogic.web.maya.clients.responce.device.DeviceInfoResponse;
import org.springframework.http.ResponseEntity;

public interface DeviceInfoClient {

    ResponseEntity<DeviceInfoResponse> getDeviceInfo(String xToken);

}