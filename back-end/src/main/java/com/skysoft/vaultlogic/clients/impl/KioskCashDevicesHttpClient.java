package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskCashDevices;
import com.skysoft.vaultlogic.clients.api.model.*;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.skysoft.vaultlogic.clients.RequestFactory.post;
import static io.vavr.API.Try;

@Service
@Profile("cloud")
@AllArgsConstructor
public class KioskCashDevicesHttpClient implements KioskCashDevices {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    private <T> ResponseEntity<T> exchange(RequestEntity<?> request, Class<T> responseType) {
        return rest.exchange(request, responseType);
    }

    @Override
    public Either<Throwable, CashDevicesStatus> getStatus(String xToken) {
        return Try(() -> exchange(post(xToken, maya::cashDeviceStatusURI), CashDevicesStatus.class))
                .map(getBody())
                .toEither();
    }

    @Override
    public Either<Throwable, RecyclerStatus> getRecyclerStatus(String xToken) {
        return Try(() -> exchange(post(xToken, maya::recyclerDeviceStatusURI), RecyclerStatus.class))
                .map(getBody())
                .toEither();
    }

    @Override
    public Either<Throwable, CashAcceptorStatus> enableCashAcceptor(String xToken, CashAcceptorConfig cashAcceptorConfig) {
        return Try(() -> exchange(post(xToken, maya::enableCashAcceptorURI, cashAcceptorConfig), CashAcceptorStatus.class))
                .map(getBody())
                .toEither();
    }

    @Override
    public Either<Throwable, CashAcceptorStatus> disableCashAcceptor(String xToken) {
        return Try(() -> exchange(post(xToken, maya::disableCashAcceptorURI), CashAcceptorStatus.class))
                .map(getBody())
                .toEither();
    }

    @Override
    public Either<Throwable, DispensableAmount> getDispensableAmount(String xToken, DispensableAmount amountToDispense) {
        return Try(() -> exchange(post(xToken, maya::dispensableAmountURI, amountToDispense), DispensableAmount.class))
                .map(getBody())
                .toEither();
    }

    @Override
    public Either<Throwable, StatusCode> dispenseCash(String xToken, DispenseCash dispenseCash) {
        return Try(() -> rest.exchange(post(xToken, maya::dispenseCashURI, dispenseCash), StatusCode.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    private <T> Function<ResponseEntity<T>, T> getBody() {
        return ResponseEntity::getBody;
    }

}