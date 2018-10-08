package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.MayaGeneralInfo;
import com.skysoft.vaultlogic.clients.api.model.KioskDevice;
import com.skysoft.vaultlogic.clients.api.model.KiosksInfo;
import com.skysoft.vaultlogic.clients.api.model.LocationsAndDevices;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.skysoft.vaultlogic.clients.RequestFactory.post;
import static com.skysoft.vaultlogic.clients.api.model.KioskId.of;
import static io.vavr.API.Try;

@Service
@AllArgsConstructor
public class MayaGeneralInfoHttpClient implements MayaGeneralInfo {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    private <T> ResponseEntity<T> exchange(RequestEntity<?> request, Class<T> responseType) {
        return rest.exchange(request, responseType);
    }

    @Override
    public Either<Throwable, LocationsAndDevices> getLocationAndDevices() {
        return Try(() -> exchange(post(maya::locationsAndDevicesURI), LocationsAndDevices.class))
                .map(getBody())
                .toEither();
    }

    @Override
    public Either<Throwable, KioskDevice> getDevice(String deviceId) {
        return Try(() -> exchange(post(maya::deviceURI, of(deviceId)), KioskDevice.class))
                .map(getBody())
                .toEither();
    }

    @Override
    public Either<Throwable, KiosksInfo> getDevices() {
        return Try(() -> exchange(post(maya::devicesURI), KiosksInfo.class))
                .map(getBody())
                .toEither();
    }

    private <T> Function<ResponseEntity<T>, T> getBody() {
        return ResponseEntity::getBody;
    }

}