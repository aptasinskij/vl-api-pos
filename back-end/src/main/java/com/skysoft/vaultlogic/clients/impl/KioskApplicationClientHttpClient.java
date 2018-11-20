package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.RequestFactory;
import com.skysoft.vaultlogic.clients.api.KioskApplicationClient;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import static com.skysoft.vaultlogic.clients.RequestFactory.post;
import static com.skysoft.vaultlogic.clients.api.model.KeepAlive.of;
import static io.vavr.API.Try;

@Slf4j
@Service
@Profile("cloud")
@AllArgsConstructor
public class KioskApplicationClientHttpClient implements KioskApplicationClient {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    @Override
    public Try<StatusCode> launchApplication(String xToken) {
        return Try(() -> rest.exchange(RequestFactory.post(xToken, maya::launchApplicationURI), StatusCode.class))
                .onFailure(throwable -> log.info("[x] failed to send launch application: {}", throwable.getMessage()))
                .map(HttpEntity::getBody);
    }

    @Override
    public Either<Throwable, StatusCode> keepAlive(String xToken, String keepAlive) {
        return Try(() -> rest.exchange(post(xToken, maya::keepAliveURI, of(keepAlive)), StatusCode.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    @Override
    public Either<Throwable, StatusCode> clientActivity(String xToken) {
        return Try(() -> rest.exchange(post(xToken, maya::clientActivityURI), StatusCode.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    @Override
    public Try<StatusCode> closeApplication(String xToken) {
        return Try(() -> rest.exchange(post(xToken, maya::closeApplicationURI), StatusCode.class))
                .map(HttpEntity::getBody);
    }

}