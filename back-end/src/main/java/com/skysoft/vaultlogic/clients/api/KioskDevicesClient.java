package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.KioskDevice;
import io.vavr.control.Try;

public interface KioskDevicesClient {

    Try<KioskDevice> getKioskInfo(String xToken);

}