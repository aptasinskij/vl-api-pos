package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskDevicesClient;
import com.skysoft.vaultlogic.clients.api.model.KioskDevice;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import static com.skysoft.vaultlogic.clients.RequestFactory.post;
import static io.vavr.API.Try;

@Service
@Profile("cloud")
@AllArgsConstructor
public class KioskDevicesClientHttpClient implements KioskDevicesClient {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    @Override
    public Try<KioskDevice> getKioskInfo(String xToken) {
        return Try(() -> rest.exchange(post(xToken, maya::deviceInfoURI), KioskDevice.class))
                .map(ResponseEntity::getBody);
    }

}