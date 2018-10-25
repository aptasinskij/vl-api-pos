package com.skysoft.vaultlogic.observers.cloud;

import com.skysoft.vaultlogic.clients.api.KioskCamera;
import com.skysoft.vaultlogic.clients.api.model.Preview;
import com.skysoft.vaultlogic.clients.api.model.PreviewConfig;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.session.projections.SessionXToken;
import com.skysoft.vaultlogic.contracts.CameraOracle;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.abi.datatypes.Event;

import javax.annotation.PostConstruct;

import java.math.BigInteger;
import java.util.function.Consumer;

import static com.skysoft.vaultlogic.contracts.CameraOracle.STARTSCANQR_EVENT;
import static com.skysoft.vaultlogic.contracts.CameraOracle.StartScanQREventResponse;

@Slf4j
@Component
@Profile("cloud")
public class StartScanQREventObserver extends AbstractContractEventObserver<StartScanQREventResponse, CameraOracle> {

    private final SessionRepository sessionRepository;
    private final KioskCamera kioskCamera;

    @Autowired
    protected StartScanQREventObserver(CameraOracle cameraOracle, SessionRepository sessionRepository, KioskCamera kioskCamera) {
        super(cameraOracle);
        this.sessionRepository = sessionRepository;
        this.kioskCamera = kioskCamera;
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
    @Transactional(readOnly = true)
    public void onNext(StartScanQREventResponse event) {
        log.info("[x] Start qr scanning: Command: {}, Session: {}, Lights: {}", event._commandId, event._sessionId, event._lights);
        SessionXToken sessionXToken = sessionRepository.findSessionXTokenById(event._sessionId);
        kioskCamera.startPreview(sessionXToken.getxToken(), PreviewConfig.of(true, event._lights))
                .onSuccess(confirmSuccessStartPreview(event._commandId))
                .onFailure(confirmFailStartPreview(event._commandId));
    }

    private Consumer<Preview> confirmSuccessStartPreview(BigInteger commandId) {
        return preview -> contract.successStart(commandId, preview.getPort(), preview.getUrl(), preview.getHref()).observable().take(1).subscribe(
                tx -> log.info("[x] confirmed success start"),
                error -> log.error("[x] error confirming success start", error)
        );
    }

    private Consumer<Throwable> confirmFailStartPreview(BigInteger commandId) {
        return throwable -> contract.failStart(commandId).observable().take(1).subscribe(
                tx -> log.info("[x] confirmed fail start preview"),
                error -> log.error("[x] error confirming start preview fail", error)
        );
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering Start Scan QR Event.", throwable);
    }

}
