package com.skysoft.vaultlogic.web.maya.clients.api.device;

import com.skysoft.vaultlogic.web.maya.clients.responseModels.device.DeviceInfo;

public interface DeviceInfoClient {

    DeviceInfo getDeviceInfo(String xToken);

}