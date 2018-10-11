package com.skysoft.vaultlogic.services.local;

import com.skysoft.vaultlogic.common.domain.kiosk.Kiosk;
import com.skysoft.vaultlogic.common.domain.kiosk.KioskRepository;
import com.skysoft.vaultlogic.contracts.KioskStorage;
import com.skysoft.vaultlogic.services.KioskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import rx.functions.Action1;

import java.util.Optional;

@Slf4j
@Service
@Profile("local")
@AllArgsConstructor
public class LocalKioskService implements KioskService {

    private static final String SHORT_ID = "F400";
    private static final String ADDRESS = "Vinnitsa";
    private static final String NAME = "Oduvanchik";
    private static final String TIMEZONE = "UTC";

    private final KioskRepository kioskRepository;
    private final KioskStorage kioskStorage;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Optional<Kiosk> resolveKioskForSession(String xToken) {
        Kiosk kiosk = kioskRepository.findByShortId(SHORT_ID).orElseGet(() -> {
            saveToSmartContract();
            return saveToDatabase();
        });
        return Optional.of(kiosk);
    }

    private Kiosk saveToDatabase() {
        return kioskRepository.save(Kiosk.kiosk(SHORT_ID, ADDRESS, NAME, TIMEZONE));
    }

    private void saveToSmartContract() {
        Action1<Throwable> onError = throwable -> log.warn("[x] error save kiosk: {}", throwable.getMessage());
        Action1<TransactionReceipt> onNext = tx -> log.info("[x] kiosk saved {}", tx.getTransactionHash());
        kioskStorage.save(SHORT_ID, ADDRESS, NAME, TIMEZONE).observable().take(1).subscribe(onNext, onError);
    }

}
