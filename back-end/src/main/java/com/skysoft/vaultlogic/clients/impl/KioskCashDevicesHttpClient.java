package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskCashDevices;
import com.skysoft.vaultlogic.clients.api.model.*;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.skysoft.vaultlogic.clients.RequestFactory.post;
import static io.vavr.API.Try;

@Slf4j
@Component
@Profile("cloud")
@AllArgsConstructor
public class KioskCashDevicesHttpClient implements KioskCashDevices {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    @Override
    public Either<Throwable, CashDevicesStatus> getStatus(String xToken) {
        return Try(() -> rest.exchange(post(xToken, maya::cashDeviceStatusURI), CashDevicesStatus.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    @Override
    public Either<Throwable, RecyclerStatus> getRecyclerStatus(String xToken) {
        return Try(() -> rest.exchange(post(xToken, maya::recyclerDeviceStatusURI), RecyclerStatus.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    @Override
    public Try<CashAcceptorStatus> enableCashAcceptor(String xToken, CashAcceptorConfig cashAcceptorConfig) {
        return Try(() -> rest.exchange(post(xToken, maya::enableCashAcceptorURI, cashAcceptorConfig), CashAcceptorStatus.class))
                .map(HttpEntity::getBody);
    }

    @Override
    public Try<CashAcceptorStatus> disableCashAcceptor(String xToken) {
        return Try(() -> rest.exchange(post(xToken, maya::disableCashAcceptorURI), CashAcceptorStatus.class))
                .map(HttpEntity::getBody);
    }

    @Override
    public Try<DispensableAmount> getDispensableAmount(String xToken, DispensableAmount amountToDispense) {
        return Try(() -> rest.exchange(post(xToken, maya::dispensableAmountURI, amountToDispense), DispensableAmount.class))
                .onFailure(throwable -> log.error("[x] fail to fetch dispensable amount: {}", throwable.getMessage()))
                .map(HttpEntity::getBody);
    }

    @Override
    public Either<Throwable, StatusCode> dispenseCash(String xToken, DispenseCash dispenseCash) {
        return Try(() -> rest.exchange(post(xToken, maya::dispenseCashURI, dispenseCash), StatusCode.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

}