package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.responseModels.device.DeviceInfo;
import com.skysoft.vaultlogic.clients.responseModels.general.AllLocationsAndDevicesInfo;
import com.skysoft.vaultlogic.clients.responseModels.general.DevicesInfo;

public interface GeneralInfoClient {

    AllLocationsAndDevicesInfo getLocationAndDevices(String xToken);

    DeviceInfo getGeneralDeviceInfo(String xToken, String deviceId);

    DevicesInfo getGeneralDevicesInfo(String xToken);

}