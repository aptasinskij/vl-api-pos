package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskApplication;
import com.skysoft.vaultlogic.clients.api.model.KeepAlive;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import io.vavr.Tuple2;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.clients.MayaHeaders.X_TOKEN_HEADER;
import static io.vavr.API.Try;

@Service
@AllArgsConstructor
public class KioskApplicationHttpClient implements KioskApplication {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    @Override
    public Either<Throwable, StatusCode> launchApplication(String xToken) {
        return Try(() -> rest.exchange(request(xToken, maya.launchApplication()), StatusCode.class))
                .map(ResponseEntity::getBody)
                .toEither();
    }

    @Override
    public Either<Throwable, StatusCode> keepAlive(Tuple2<String, String> tokens) {
        return Try(() -> rest.exchange(keepAlive(tokens._1, tokens._2), StatusCode.class))
                .map(ResponseEntity::getBody)
                .toEither();
    }

    @Override
    public Either<Throwable, StatusCode> clientActivity(String xToken) {
        return Try(() -> rest.exchange(request(xToken, maya.clientActivity()), StatusCode.class))
                .map(ResponseEntity::getBody)
                .toEither();
    }

    @Override
    public Either<Throwable, StatusCode> closeApplication(String xToken) {
        return Try(() -> rest.exchange(request(xToken, maya.closeApplication()), StatusCode.class))
                .map(ResponseEntity::getBody)
                .toEither();
    }

    private RequestEntity<Void> request(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(X_TOKEN_HEADER, xToken)
                .build();
    }

    private RequestEntity<KeepAlive> keepAlive(String xToken, String keepAliveToken) {
        return RequestEntity.post(URI.create(maya.getKeepAliveUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(KeepAlive.of(keepAliveToken));
    }

}