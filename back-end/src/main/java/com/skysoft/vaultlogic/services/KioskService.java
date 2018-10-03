package com.skysoft.vaultlogic.services;

import com.skysoft.vaultlogic.common.domain.kiosk.Kiosk;

public interface KioskService {

    Kiosk resolveKioskForSession(String xToken);

}
