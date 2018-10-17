package com.skysoft.vaultlogic.postback.impl.handlers;

import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.session.projections.SessionId;
import com.skysoft.vaultlogic.contracts.CameraOracle;
import com.skysoft.vaultlogic.postback.api.AbstractEventHandler;
import com.skysoft.vaultlogic.postback.api.EventEmptyResponse;
import com.skysoft.vaultlogic.postback.impl.factories.markers.ScannerActionHandler;
import com.skysoft.vaultlogic.postback.impl.protocol.data.QRScanned;
import com.skysoft.vaultlogic.postback.mapper.DataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Profile("cloud")
public class QRScannedActionHandler extends AbstractEventHandler<QRScanned, EventEmptyResponse> implements ScannerActionHandler {

    private static final String QR_SCANNED_HANDLER = "qr-scanned";

    private final CameraOracle cameraOracle;
    private final SessionRepository sessionRepository;

    @Autowired
    protected QRScannedActionHandler(DataMapper dataMapper, CameraOracle cameraOracle, SessionRepository sessionRepository) {
        super(QRScanned.class, dataMapper);
        this.cameraOracle = cameraOracle;
        this.sessionRepository = sessionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public EventEmptyResponse handleEvent(QRScanned event, String xToken) {
        log.info("[x] ---> POST BACK: QR code scanned: {}", xToken);
        SessionId sessionId = sessionRepository.findSessionIdByXToken(xToken);
        cameraOracle.scanned(sessionId.getId(), event.data).observable().take(1).subscribe(
                tx -> log.info("[x] confirmed qr scanned"),
                error -> log.error("[x] error qr scanned confirmation")
        );
        return new EventEmptyResponse();
    }

    @Override
    public String getName() {
        return QR_SCANNED_HANDLER;
    }

}
