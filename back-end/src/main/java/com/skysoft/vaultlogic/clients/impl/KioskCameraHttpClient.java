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

import java.net.URI;
import java.util.function.Function;

import static com.skysoft.vaultlogic.clients.MayaHeaders.X_TOKEN_HEADER;
import static io.vavr.API.Try;

@Service
@AllArgsConstructor
public class KioskCameraHttpClient implements KioskCamera {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    @Override
    public Either<Throwable, PhotoId> takePhoto(String xToken) {
        return Try(() -> rest.exchange(buildRequestEntity(xToken, maya.getTakePhotoUrl()), PhotoId.class))
                .map(getBody())
                .toEither();
    }

    @Override
    public Either<Throwable, ScanId> takeScan(String xToken, ScanLight scanLight) {
        return Try(() -> rest.exchange(buildTakeScanRequestEntity(xToken, scanLight), ScanId.class))
                .map(getBody())
                .toEither();
    }

    @Override
    public Either<Throwable, Preview> startPreview(String xToken, PreviewConfig startPreview) {
        return Try(() -> rest.exchange(buildStartPreviewRequestEntity(xToken, startPreview), Preview.class))
                .map(getBody())
                .toEither();
    }

    @Override
    public Either<Throwable, StatusCode> stopPreview(String xToken) {
        return Try(() -> rest.exchange(buildRequestEntity(xToken, maya.getStopPreviewUrl()), StatusCode.class))
                .map(getBody())
                .toEither();
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(X_TOKEN_HEADER, xToken)
                .build();
    }

    private RequestEntity<ScanLight> buildTakeScanRequestEntity(String xToken, ScanLight takeScan) {
        return RequestEntity.post(URI.create(maya.getTakeScanUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(takeScan);
    }

    private RequestEntity<PreviewConfig> buildStartPreviewRequestEntity(String xToken, PreviewConfig startPreview) {
        return RequestEntity.post(URI.create(maya.getStartPreviewUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(startPreview);
    }

    private <T> Function<ResponseEntity<T>, T> getBody() {
        return ResponseEntity::getBody;
    }

}