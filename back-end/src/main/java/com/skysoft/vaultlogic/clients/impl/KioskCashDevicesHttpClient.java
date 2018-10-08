package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskCashDevices;
import com.skysoft.vaultlogic.clients.api.model.*;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.clients.MayaHeaders.X_TOKEN_HEADER;
import static io.vavr.API.Try;

@Service
@AllArgsConstructor
public class KioskCashDevicesHttpClient implements KioskCashDevices {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    @Override
    public Either<Throwable, CashDevicesStatus> getStatus(String xToken) {
        return Try(() -> rest.exchange(buildRequestEntity(xToken, maya.getCashDeviceStatusUrl()), CashDevicesStatus.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    @Override
    public Either<Throwable, RecyclerStatus> getRecyclerStatus(String xToken) {
        return Try(() -> rest.exchange(buildRequestEntity(xToken, maya.getRecyclerDeviceStatusUrl()), RecyclerStatus.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    @Override
    public Either<Throwable, CashAcceptorStatus> enableCashAcceptor(String xToken, CashAcceptorConfig cashAcceptorConfig) {
        return Try(() -> rest.exchange(buildEnableCashAcceptorRequestEntity(xToken, cashAcceptorConfig), CashAcceptorStatus.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    @Override
    public Either<Throwable, CashAcceptorStatus> disableCashAcceptor(String xToken) {
        return Try(() -> rest.exchange(buildRequestEntity(xToken, maya.getDisableCashAcceptorUrl()), CashAcceptorStatus.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    @Override
    public Either<Throwable, DispensableAmount> getDispensableAmount(String xToken, DispensableAmount amountToDispense) {
        return Try(() -> rest.exchange(buildGetDispensableAmountRequestEntity(xToken, amountToDispense), DispensableAmount.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    @Override
    public Either<Throwable, StatusCode> dispenseCash(String xToken, DispenseCash dispenseCash) {
        return Try(() -> rest.exchange(buildDispenseCashRequestEntity(xToken, dispenseCash), StatusCode.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(X_TOKEN_HEADER, xToken)
                .build();
    }

    private RequestEntity<CashAcceptorConfig> buildEnableCashAcceptorRequestEntity(String xToken, CashAcceptorConfig acceptorConfig) {
        return RequestEntity.post(URI.create(maya.getEnableCashAcceptorUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(acceptorConfig);
    }

    private RequestEntity<DispensableAmount> buildGetDispensableAmountRequestEntity(String xToken, DispensableAmount amountToDispense) {
        return RequestEntity.post(URI.create(maya.getDispensableAmountUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(amountToDispense);
    }

    private RequestEntity<DispenseCash> buildDispenseCashRequestEntity(String xToken, DispenseCash dispenseCash) {
        return RequestEntity.post(URI.create(maya.getDispenseCashUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(dispenseCash);
    }

}