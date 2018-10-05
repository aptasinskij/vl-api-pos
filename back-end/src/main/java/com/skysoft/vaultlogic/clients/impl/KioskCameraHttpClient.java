package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskCamera;
import com.skysoft.vaultlogic.clients.api.model.*;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.clients.MayaHeaders.X_TOKEN_HEADER;

@Service
@AllArgsConstructor
public class KioskCameraHttpClient implements KioskCamera {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    @Override
    public PhotoId takePhoto(String xToken) {
        try {
            ResponseEntity<PhotoId> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getTakePhotoUrl()), PhotoId.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ScanId takeScan(String xToken, ScanLight scanLight) {
        try {
            ResponseEntity<ScanId> exchange = oAuth2RestTemplate.exchange(buildTakeScanRequestEntity(xToken, scanLight), ScanId.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Preview startPreview(String xToken, PreviewConfig startPreview) {
        try {
            ResponseEntity<Preview> exchange = oAuth2RestTemplate.exchange(buildStartPreviewRequestEntity(xToken, startPreview), Preview.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public StatusCode stopPreview(String xToken) {
        try {
            ResponseEntity<StatusCode> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getStopPreviewUrl()), StatusCode.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(X_TOKEN_HEADER, xToken)
                .build();
    }

    private RequestEntity<ScanLight> buildTakeScanRequestEntity(String xToken, ScanLight takeScan) {
        return RequestEntity.post(URI.create(mayaProperties.getTakeScanUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(takeScan);
    }

    private RequestEntity<PreviewConfig> buildStartPreviewRequestEntity(String xToken, PreviewConfig startPreview) {
        return RequestEntity.post(URI.create(mayaProperties.getStartPreviewUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(startPreview);
    }



}