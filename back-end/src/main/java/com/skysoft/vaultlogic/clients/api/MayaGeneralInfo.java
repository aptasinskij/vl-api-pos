package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.KioskDevice;
import com.skysoft.vaultlogic.clients.api.model.LocationsAndDevices;
import com.skysoft.vaultlogic.clients.api.model.KiosksInfo;

public interface MayaGeneralInfo {

    LocationsAndDevices getLocationAndDevices(String xToken);

    KioskDevice getDevice(String deviceId);

    KiosksInfo getDevices();

}