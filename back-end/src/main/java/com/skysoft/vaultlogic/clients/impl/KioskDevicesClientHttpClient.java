package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskDevicesClient;
import com.skysoft.vaultlogic.clients.api.model.KioskDevice;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import static com.skysoft.vaultlogic.clients.RequestFactory.post;
import static io.vavr.API.Try;

@Slf4j
@Service
@Profile("cloud")
@AllArgsConstructor
public class KioskDevicesClientHttpClient implements KioskDevicesClient {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    @Override
    public Try<KioskDevice> getKioskInfo(String xToken) {
        Try<ResponseEntity<KioskDevice>> kioskInfo = Try(() -> rest.exchange(post(xToken, maya::deviceInfoURI), KioskDevice.class));
        kioskInfo.onFailure(throwable -> log.error("[x] failed to send kiosk info request: {}", throwable.getMessage()));
        return kioskInfo.map(ResponseEntity::getBody);
    }

}