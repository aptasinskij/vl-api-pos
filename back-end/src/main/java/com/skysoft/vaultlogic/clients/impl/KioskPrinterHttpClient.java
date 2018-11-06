package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskPrinter;
import com.skysoft.vaultlogic.clients.api.model.Receipt;
import com.skysoft.vaultlogic.clients.api.model.ReceiptIdUrl;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import static com.skysoft.vaultlogic.clients.RequestFactory.post;
import static io.vavr.API.Try;

@Slf4j
@Service
@Profile("cloud")
@AllArgsConstructor
public class KioskPrinterHttpClient implements KioskPrinter {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    @Override
    public Try<ReceiptIdUrl> createReceipt(String xToken) {
        return Try(() -> rest.exchange(post(xToken, maya::createReceiptURI), ReceiptIdUrl.class))
                .onFailure(throwable -> log.error("[x] create receipt request failed with message:", throwable))
                .map(HttpEntity::getBody);
    }

    @Override
    public Try<StatusCode> printReceipt(String xToken, Receipt receipt) {
        return Try(() -> rest.exchange(post(xToken, maya::printReceiptURI, receipt), StatusCode.class))
                .map(HttpEntity::getBody);
    }

}