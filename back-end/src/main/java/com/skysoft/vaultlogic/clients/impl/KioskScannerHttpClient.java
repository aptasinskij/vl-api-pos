package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskScanner;
import com.skysoft.vaultlogic.clients.api.model.ScannerStatus;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import static com.skysoft.vaultlogic.clients.RequestFactory.post;
import static io.vavr.API.Try;

@Service
@Profile("cloud")
@AllArgsConstructor
public class KioskScannerHttpClient implements KioskScanner {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    private <T> ResponseEntity<T> exchange(RequestEntity<?> request, Class<T> responseType) {
        return rest.exchange(request, responseType);
    }

    @Override
    public Either<Throwable, ScannerStatus> getStatus(String xToken) {
        return Try(() -> exchange(post(xToken, maya::scannerStatusURI), ScannerStatus.class))
                .map(ResponseEntity::getBody)
                .toEither();
    }

}
