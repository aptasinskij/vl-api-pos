package com.skysoft.vaultlogic.web.service;

import com.skysoft.vaultlogic.common.domain.kiosk.Kiosk;

public interface KioskService {

    Kiosk forSession(String xToken);

}