package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskDevices;
import com.skysoft.vaultlogic.clients.api.model.KioskDevice;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.clients.MayaHeaders.X_TOKEN_HEADER;
import static io.vavr.API.Try;

@Service
@AllArgsConstructor
public class DeviceInfoHttpClient implements KioskDevices {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    @Override
    public Either<Throwable, KioskDevice> getKioskInfo(String xToken) {
        return Try(() -> rest.exchange(buildRequestEntity(xToken, maya.getDeviceInfoUrl()), KioskDevice.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(X_TOKEN_HEADER, xToken)
                .build();
    }

}