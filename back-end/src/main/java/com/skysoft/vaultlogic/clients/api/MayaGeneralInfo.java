package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.KioskDevice;
import com.skysoft.vaultlogic.clients.api.model.LocationsAndDevices;
import com.skysoft.vaultlogic.clients.api.model.KiosksInfo;
import io.vavr.control.Either;

public interface MayaGeneralInfo {

    Either<Throwable, LocationsAndDevices> getLocationAndDevices();

    Either<Throwable, KioskDevice> getDevice(String deviceId);

    Either<Throwable, KiosksInfo> getDevices();

}