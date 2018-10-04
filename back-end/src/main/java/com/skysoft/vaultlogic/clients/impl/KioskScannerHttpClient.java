package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskScanner;
import com.skysoft.vaultlogic.clients.api.model.ScannerStatus;
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
public class KioskScannerHttpClient implements KioskScanner {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    @Override
    public ScannerStatus getStatus(String xToken) {
        try {
            ResponseEntity<ScannerStatus> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getScannerStatusUrl()), ScannerStatus.class);
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

}
