package com.skysoft.vaultlogic.observers.cloud;

import com.skysoft.vaultlogic.contracts.CameraOracle;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import static com.skysoft.vaultlogic.contracts.CameraOracle.STARTSCANQR_EVENT;
import static com.skysoft.vaultlogic.contracts.CameraOracle.StartScanQREventResponse;

@Slf4j
@Component
@Profile("cloud")
public class StartScanQREventObserver extends AbstractContractEventObserver<StartScanQREventResponse, CameraOracle> {

    @Autowired
    protected StartScanQREventObserver(CameraOracle cameraOracle) {
        super(cameraOracle);
        subscribe();
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
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering Start Scan QR Event.", throwable);
    }

}
