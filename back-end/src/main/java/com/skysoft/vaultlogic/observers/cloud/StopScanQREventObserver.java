package com.skysoft.vaultlogic.observers.cloud;

import com.skysoft.vaultlogic.clients.api.KioskCamera;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.session.projections.SessionXToken;
import com.skysoft.vaultlogic.contracts.CameraOracle;
import com.skysoft.vaultlogic.contracts.CameraOracle.StopScanQREventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import javax.annotation.PostConstruct;

import java.math.BigInteger;
import java.util.function.Consumer;

import static com.skysoft.vaultlogic.contracts.CameraOracle.STOPSCANQR_EVENT;

@Slf4j
@Component
@Profile("cloud")
public class StopScanQREventObserver extends AbstractContractEventObserver<StopScanQREventResponse, CameraOracle> {

    private final KioskCamera kioskCamera;
    private final SessionRepository sessionRepository;

    @Autowired
    protected StopScanQREventObserver(CameraOracle cameraOracle, KioskCamera kioskCamera, SessionRepository sessionRepository) {
        super(cameraOracle);
        this.kioskCamera = kioskCamera;
        this.sessionRepository = sessionRepository;
    }

    @Override
    @PostConstruct
    protected void subscribe() {
        super.subscribe();
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
        log.info("[x] Stop qr scanning: Command: {}, Session: {}", event._commandId, event._sessionId);
        SessionXToken sessionXToken = sessionRepository.findSessionXTokenById(event._sessionId);
        kioskCamera.stopPreview(sessionXToken.xToken)
                .onSuccess(confirmSuccessStopPreview(event._commandId))
                .onFailure(confirmFailStopPreview(event._commandId));
    }

    private Consumer<StatusCode> confirmSuccessStopPreview(BigInteger commandId) {
        return statusCode -> contract.successStop(commandId).observable().take(1).subscribe(
                tx -> log.info("[x] confirmed success stop preview"),
                error -> log.error("[x] error confirming success preview stop")
        );
    }

    private Consumer<Throwable> confirmFailStopPreview(BigInteger commandId) {
        return throwable -> contract.failStop(commandId).observable().take(1).subscribe(
                tx -> log.info("[x] confirmed fail stop preview"),
                error -> log.error("[x] error confirming fail preview stop")
        );
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering Stop Scan QR Event.", throwable);
    }

}
