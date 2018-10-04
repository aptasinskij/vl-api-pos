package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskPrinter;
import com.skysoft.vaultlogic.clients.api.model.Receipt;
import com.skysoft.vaultlogic.clients.api.model.ReceiptIdUrl;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.clients.MayaHeaders.X_TOKEN_HEADER;


@Service
@AllArgsConstructor
public class KioskPrinterHttpClient implements KioskPrinter {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    @Override
    public ReceiptIdUrl createReceipt(String xToken) {
        try {
            ResponseEntity<ReceiptIdUrl> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getCreateReceiptUrl()), ReceiptIdUrl.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public StatusCode printReceipt(String xToken, Receipt receipt) {
        try {
            ResponseEntity<StatusCode> exchange = oAuth2RestTemplate.exchange(buildPrintReceiptRequestEntity(xToken, receipt), StatusCode.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(X_TOKEN_HEADER, xToken)
                .build();
    }

    private RequestEntity<Receipt> buildPrintReceiptRequestEntity(String xToken, Receipt receipt) {
        return RequestEntity.post(URI.create(mayaProperties.getPrintReceiptUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(receipt);
    }

}