package com.skysoft.vaultlogic.services;

import com.skysoft.vaultlogic.common.domain.kiosk.Kiosk;
import com.skysoft.vaultlogic.common.domain.kiosk.KioskRepository;
import com.skysoft.vaultlogic.contracts.KioskStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Profile("ganache")
public class ImMemoryKioskService implements KioskService {

    private static final String KIOSK_SHORT_ID = "F400";

    private final KioskRepository kioskRepository;
    private final KioskStorage kioskStorage;

    @Autowired
    public ImMemoryKioskService(KioskRepository kioskRepository, KioskStorage kioskStorage) {
        this.kioskRepository = kioskRepository;
        this.kioskStorage = kioskStorage;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Kiosk resolveKioskForSession(String xToken) {
        return kioskRepository.findByShortId(KIOSK_SHORT_ID)
                .orElseGet(() -> {
                    Kiosk kiosk = Kiosk.kiosk(KIOSK_SHORT_ID, "Vinnitsa", "Oduvanchik", "UTC");
                    kioskStorage.save(kiosk.getShortId(), kiosk.getAddress(), kiosk.getName(), kiosk.getTimezone())
                            .sendAsync()
                            .thenAccept(tx -> log.info("[x] New KioskDevices saved. Hash: {}", tx.getTransactionHash()))
                            .exceptionally(throwable -> {
                                log.error("[x] Error when save kiosk: {}", throwable);
                                return null;
                            });
                    return kioskRepository.save(kiosk);
                });
    }

}
