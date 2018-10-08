package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskPrinter;
import com.skysoft.vaultlogic.clients.api.model.Receipt;
import com.skysoft.vaultlogic.clients.api.model.ReceiptIdUrl;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
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
public class KioskPrinterHttpClient implements KioskPrinter {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    @Override
    public Either<Throwable, ReceiptIdUrl> createReceipt(String xToken) {
        return Try(() -> rest.exchange(buildRequestEntity(xToken, maya.getCreateReceiptUrl()), ReceiptIdUrl.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    @Override
    public Either<Throwable, StatusCode> printReceipt(String xToken, Receipt receipt) {
        return Try(() -> rest.exchange(buildPrintReceiptRequestEntity(xToken, receipt), StatusCode.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(X_TOKEN_HEADER, xToken)
                .build();
    }

    private RequestEntity<Receipt> buildPrintReceiptRequestEntity(String xToken, Receipt receipt) {
        return RequestEntity.post(URI.create(maya.getPrintReceiptUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(receipt);
    }

}