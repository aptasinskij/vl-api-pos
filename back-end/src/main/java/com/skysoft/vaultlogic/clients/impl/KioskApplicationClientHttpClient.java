package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskApplicationClient;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.skysoft.vaultlogic.clients.RequestFactory.post;
import static com.skysoft.vaultlogic.clients.api.model.KeepAlive.of;
import static io.vavr.API.Try;

@Service
@AllArgsConstructor
public class KioskApplicationClientHttpClient implements KioskApplicationClient {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    private <T> ResponseEntity<T> exchange(RequestEntity<?> request, Class<T> responseType) {
        return rest.exchange(request, responseType);
    }

    @Override
    public Try<StatusCode> launchApplication(String xToken) {
        return Try(() -> exchange(post(xToken, maya::launchApplicationURI), StatusCode.class))
                .map(getBody());
    }

    @Override
    public Either<Throwable, StatusCode> keepAlive(String xToken, String keepAlive) {
        return Try(() -> exchange(post(xToken, maya::keepAliveURI, of(keepAlive)), StatusCode.class))
                .map(getBody())
                .toEither();
    }

    @Override
    public Either<Throwable, StatusCode> clientActivity(String xToken) {
        return Try(() -> exchange(post(xToken, maya::clientActivityURI), StatusCode.class))
                .map(getBody())
                .toEither();
    }

    @Override
    public Either<Throwable, StatusCode> closeApplication(String xToken) {
        return Try(() -> exchange(post(xToken, maya::closeApplicationURI), StatusCode.class))
                .map(getBody())
                .toEither();
    }

    private <T> Function<ResponseEntity<T>, T> getBody() {
        return ResponseEntity::getBody;
    }

}