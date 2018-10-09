package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.KioskDevice;
import io.vavr.control.Either;

public interface KioskDevices {

    Either<Throwable, KioskDevice> getKioskInfo(String xToken);

}