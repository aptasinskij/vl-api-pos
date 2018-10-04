package com.skysoft.vaultlogic.clients.api.device;

import com.skysoft.vaultlogic.clients.responseModels.device.DeviceInfo;

public interface DeviceInfoClient {

    DeviceInfo getDeviceInfo(String xToken);

}