package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.MayaGeneralInfo;
import com.skysoft.vaultlogic.clients.api.model.KioskDevice;
import com.skysoft.vaultlogic.clients.api.model.KioskId;
import com.skysoft.vaultlogic.clients.api.model.KiosksInfo;
import com.skysoft.vaultlogic.clients.api.model.LocationsAndDevices;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static io.vavr.API.Try;

@Service
@AllArgsConstructor
public class MayaGeneralInfoHttpClient implements MayaGeneralInfo {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    @Override
    public Either<Throwable, LocationsAndDevices> getLocationAndDevices() {
        return Try(() -> rest.exchange(buildRequestEntity(maya.getLocationAndDevicesUrl()), LocationsAndDevices.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    @Override
    public Either<Throwable, KioskDevice> getDevice(String deviceId) {
        return Try(() -> rest.exchange(buildGetDeviceInfoRequestEntity(deviceId), KioskDevice.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    @Override
    public Either<Throwable, KiosksInfo> getDevices() {
        return Try(() -> rest.exchange(buildRequestEntity(maya.getDevicesUrl()), KiosksInfo.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    private RequestEntity<Void> buildRequestEntity(String url) {
        return RequestEntity.post(URI.create(url))
                .build();
    }

    private RequestEntity<KioskId> buildGetDeviceInfoRequestEntity(String deviceId) {
        return RequestEntity.post(URI.create(maya.getDeviceUrl()))
                .body(KioskId.of(deviceId));
    }

}