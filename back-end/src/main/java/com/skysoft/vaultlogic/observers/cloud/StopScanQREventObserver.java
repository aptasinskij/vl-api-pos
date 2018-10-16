package com.skysoft.vaultlogic.observers.cloud;

import com.skysoft.vaultlogic.contracts.CameraOracle;
import com.skysoft.vaultlogic.contracts.CameraOracle.StopScanQREventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import static com.skysoft.vaultlogic.contracts.CameraOracle.STOPSCANQR_EVENT;

@Slf4j
@Component
@Profile("cloud")
public class StopScanQREventObserver extends AbstractContractEventObserver<StopScanQREventResponse, CameraOracle> {

    @Autowired
    protected StopScanQREventObserver(CameraOracle cameraOracle) {
        super(cameraOracle);
        subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return STOPSCANQR_EVENT;
    }

    @Override
    protected EventObservable<StopScanQREventResponse> getEventObservable() {
        return contract::stopScanQREventObservable;
    }

    @Override
    public void onNext(StopScanQREventResponse event) {
        log.info("[x] Stop qr scanning: {}, {}", event._commandId, event._sessionId);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering Stop Scan QR Event.", throwable);
    }

}
