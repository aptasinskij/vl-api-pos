package com.skysoft.vaultlogic.web.service;

import com.skysoft.vaultlogic.common.domain.kiosk.Kiosk;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("ganache")
public class ImMemoryKioskService implements KioskService {

    @Override
    public Kiosk forSession(String xToken) {
        return Kiosk.kiosk("Kiosk F400", "Vinnitsa", "Oduvanchik", "UTC");
    }

}
