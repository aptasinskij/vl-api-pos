package com.skysoft.vaultlogic.services.cloud;

import com.skysoft.vaultlogic.clients.api.KioskDevicesClient;
import com.skysoft.vaultlogic.clients.api.model.KioskDevice;
import com.skysoft.vaultlogic.common.domain.kiosk.Kiosk;
import com.skysoft.vaultlogic.common.domain.kiosk.KioskRepository;
import com.skysoft.vaultlogic.common.domain.kiosk.mapper.KioskMapper;
import com.skysoft.vaultlogic.contracts.KioskStorage;
import com.skysoft.vaultlogic.services.KioskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import rx.functions.Action1;

import java.util.Optional;

import static io.vavr.API.*;
import static io.vavr.Patterns.$Failure;
import static io.vavr.Patterns.$Success;

@Slf4j
@Service
@Profile("cloud")
public class KioskServiceImpl implements KioskService {

    private final KioskDevicesClient kioskDevices;
    private final KioskRepository kioskRepository;
    private final KioskMapper kioskMapper;
    private final KioskStorage kioskStorage;

    private Action1<Throwable> onError = throwable -> log.warn("[x] error save kiosk: {}", throwable.getMessage());
    private Action1<TransactionReceipt> onNext = tx -> log.info("[x] kiosk saved {}", tx.getTransactionHash());

    public KioskServiceImpl(KioskDevicesClient kioskDevices, KioskRepository kioskRepository, KioskMapper kioskMapper, KioskStorage kioskStorage) {
        this.kioskDevices = kioskDevices;
        this.kioskRepository = kioskRepository;
        this.kioskMapper = kioskMapper;
        this.kioskStorage = kioskStorage;
    }

    @Override
    @Transactional
    public Optional<Kiosk> resolveKioskForSession(String xToken) {
        return Match(kioskDevices.getKioskInfo(xToken)).option(
                Case($Success($()), this::resolveFromRepository),
                Case($Failure($()), error -> null)
        ).toJavaOptional();
    }

    private Kiosk resolveFromRepository(KioskDevice device) {
        Kiosk kiosk = kioskMapper.fromKioskDevice(device);
        return kioskRepository.findByShortId(kiosk.getShortId()).orElseGet(() -> {
            /*saveToSmartContract(kiosk);*/
            return kioskRepository.save(kiosk);
        });
    }

    private void saveToSmartContract(Kiosk kiosk) {
        kioskStorage.save(kiosk.getShortId(), kiosk.getAddress(), kiosk.getName(), kiosk.getTimezone()).observable()
                .take(1)
                .subscribe(onNext, onError);
    }

}
