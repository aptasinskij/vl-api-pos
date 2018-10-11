package com.skysoft.vaultlogic.services;

import com.skysoft.vaultlogic.common.domain.kiosk.Kiosk;

import java.util.Optional;

public interface KioskService {

    Optional<Kiosk> resolveKioskForSession(String xToken);

}
