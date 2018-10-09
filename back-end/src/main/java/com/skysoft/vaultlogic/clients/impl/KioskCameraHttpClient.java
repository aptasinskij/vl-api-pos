package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskCamera;
import com.skysoft.vaultlogic.clients.api.model.*;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.skysoft.vaultlogic.clients.RequestFactory.post;
import static io.vavr.API.Try;

@Service
@AllArgsConstructor
public class KioskCameraHttpClient implements KioskCamera {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    private <T> ResponseEntity<T> exchange(RequestEntity<?> request, Class<T> responseType) {
        return rest.exchange(request, responseType);
    }

    @Override
    public Either<Throwable, PhotoId> takePhoto(String xToken) {
        return Try(() -> exchange(post(xToken, maya::takePhotoURI), PhotoId.class))
                .map(getBody())
                .toEither();
    }

    @Override
    public Either<Throwable, ScanId> takeScan(String xToken, ScanLight scanLight) {
        return Try(() -> exchange(post(xToken, maya::takeScanURI, scanLight), ScanId.class))
                .map(getBody())
                .toEither();
    }

    @Override
    public Either<Throwable, Preview> startPreview(String xToken, PreviewConfig startPreview) {
        return Try(() -> exchange(post(xToken, maya::startPreviewURI, startPreview), Preview.class))
                .map(getBody())
                .toEither();
    }

    @Override
    public Either<Throwable, StatusCode> stopPreview(String xToken) {
        return Try(() -> exchange(post(xToken, maya::stopPreviewURI), StatusCode.class))
                .map(getBody())
                .toEither();
    }

    private <T> Function<ResponseEntity<T>, T> getBody() {
        return ResponseEntity::getBody;
    }

}