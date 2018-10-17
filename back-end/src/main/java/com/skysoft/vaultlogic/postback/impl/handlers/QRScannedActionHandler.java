package com.skysoft.vaultlogic.postback.impl.handlers;

import com.skysoft.vaultlogic.postback.api.AbstractEventHandler;
import com.skysoft.vaultlogic.postback.api.EventEmptyResponse;
import com.skysoft.vaultlogic.postback.impl.factories.markers.ScannerActionHandler;
import com.skysoft.vaultlogic.postback.impl.protocol.data.QRScanned;
import com.skysoft.vaultlogic.postback.mapper.DataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("cloud")
public class QRScannedActionHandler extends AbstractEventHandler<QRScanned, EventEmptyResponse> implements ScannerActionHandler {

    private static final String QR_SCANNED_HANDLER = "qr-scanned";

    @Autowired
    protected QRScannedActionHandler(DataMapper dataMapper) {
        super(QRScanned.class, dataMapper);
    }

    @Override
    protected EventEmptyResponse handleEvent(QRScanned eventData, String xToken) {
        log.info("[x] ---> QR code scanned: {}", xToken);
        return new EventEmptyResponse();
    }

    @Override
    public String getName() {
        return QR_SCANNED_HANDLER;
    }

}
