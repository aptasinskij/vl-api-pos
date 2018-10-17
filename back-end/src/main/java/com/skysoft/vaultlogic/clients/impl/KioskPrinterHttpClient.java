package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskPrinter;
import com.skysoft.vaultlogic.clients.api.model.Receipt;
import com.skysoft.vaultlogic.clients.api.model.ReceiptIdUrl;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import io.vavr.control.Either;
import io.vavr.control.Try;
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
public class KioskPrinterHttpClient implements KioskPrinter {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    private <T> ResponseEntity<T> exchange(RequestEntity<?> request, Class<T> responseType) {
        return rest.exchange(request, responseType);
    }

    @Override
    public Try<ReceiptIdUrl> createReceipt(String xToken) {
        return Try(() -> exchange(post(xToken, maya::createReceiptURI), ReceiptIdUrl.class))
                .map(HttpEntity::getBody);
    }

    @Override
    public Try<StatusCode> printReceipt(String xToken, Receipt receipt) {
        return Try(() -> exchange(post(xToken, maya::printReceiptURI, receipt), StatusCode.class))
                .map(HttpEntity::getBody);
    }

    private <T> Function<ResponseEntity<T>, T> getBody() {
        return ResponseEntity::getBody;
    }

}