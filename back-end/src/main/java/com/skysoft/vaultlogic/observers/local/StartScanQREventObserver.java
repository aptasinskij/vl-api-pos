package com.skysoft.vaultlogic.observers.local;

import com.skysoft.vaultlogic.contracts.CameraOracle;
import com.skysoft.vaultlogic.contracts.CameraOracle.StartScanQREventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import javax.annotation.PostConstruct;
import java.util.UUID;

import static com.skysoft.vaultlogic.contracts.CameraOracle.STARTSCANQR_EVENT;

@Slf4j
@Component
@Profile("local")
public class StartScanQREventObserver extends AbstractContractEventObserver<StartScanQREventResponse, CameraOracle> {

    @Autowired
    public StartScanQREventObserver(CameraOracle cameraOracle) {
        super(cameraOracle);
    }

    @Override
    @PostConstruct
    protected void subscribe() {
        super.subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return STARTSCANQR_EVENT;
    }

    @Override
    protected EventObservable<StartScanQREventResponse> getEventObservable() {
        return contract::startScanQREventObservable;
    }

    @Override
    public void onNext(StartScanQREventResponse event) {
        log.info("[x] Start qr scanning: {}, {}", event._commandId, event._sessionId, event._lights);
        String randomUUID = UUID.randomUUID().toString();
        contract.successStart(event._sessionId, randomUUID, randomUUID, randomUUID).observable().take(1).subscribe(
                tx -> log.info("[x] Confirmed, TX: {}", tx.getTransactionHash()),
                th -> log.error("[x] Error during confirmation", th)
        );
        contract.scanned(event._sessionId, randomUUID).observable().take(1).subscribe(
                tx -> log.info("[x] QR DATA. {}, Confirmed: {}", randomUUID, tx.getTransactionHash()),
                error -> log.error("[x] Error send scanned data", error)
        );
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering Start Scan QR Event.", throwable);
    }

}
