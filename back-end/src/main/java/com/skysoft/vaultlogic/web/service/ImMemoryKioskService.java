package com.skysoft.vaultlogic.web.service;

import com.skysoft.vaultlogic.common.domain.kiosk.Kiosk;
import com.skysoft.vaultlogic.common.domain.kiosk.KioskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Profile("ganache")
public class ImMemoryKioskService implements KioskService {

    private final KioskRepository kioskRepository;

    @Autowired
    public ImMemoryKioskService(KioskRepository kioskRepository) {
        this.kioskRepository = kioskRepository;
    }

    @Override
    @Transactional
    public Kiosk forSession(String xToken) {
        return kioskRepository.save(Kiosk.kiosk("Kiosk F400", "Vinnitsa", "Oduvanchik", "UTC"));
    }

}
