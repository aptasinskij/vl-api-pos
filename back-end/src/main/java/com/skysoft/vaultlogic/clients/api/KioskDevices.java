package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.KioskDevice;

public interface KioskDevices {

    KioskDevice getKioskInfo(String xToken);

}