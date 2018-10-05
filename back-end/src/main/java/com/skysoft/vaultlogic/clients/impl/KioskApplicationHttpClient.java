package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskApplication;
import com.skysoft.vaultlogic.clients.api.model.KeepAlive;
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
public class KioskApplicationHttpClient implements KioskApplication {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    @Override
    public StatusCode launchApplication(String xToken) {
        try {
            ResponseEntity<StatusCode> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getLaunchApplicationUrl()), StatusCode.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public StatusCode keepAlive(String xToken, String keepAliveToken) {
        try {
            ResponseEntity<StatusCode> exchange = oAuth2RestTemplate.exchange(buildKeepAliveRequestEntity(xToken, keepAliveToken), StatusCode.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public StatusCode clientActivity(String xToken) {
        try {
            ResponseEntity<StatusCode> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getClientActivityUrl()), StatusCode.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public StatusCode closeApplication(String xToken) {
        try {
            ResponseEntity<StatusCode> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getCloseApplicationUrl()), StatusCode.class);
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

    private RequestEntity<KeepAlive> buildKeepAliveRequestEntity(String xToken, String keepAliveToken) {
        return RequestEntity.post(URI.create(mayaProperties.getKeepAliveUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(KeepAlive.of(keepAliveToken));
    }

}