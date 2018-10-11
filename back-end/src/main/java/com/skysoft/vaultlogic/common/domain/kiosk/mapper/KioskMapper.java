package com.skysoft.vaultlogic.common.domain.kiosk.mapper;

import com.skysoft.vaultlogic.clients.api.model.KioskDevice;
import com.skysoft.vaultlogic.common.domain.kiosk.Kiosk;

public interface KioskMapper {
    Kiosk fromKioskDevice(KioskDevice device);
}
