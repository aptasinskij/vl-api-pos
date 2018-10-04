package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.AccessClient;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import com.skysoft.vaultlogic.clients.responces.BaseResponse;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.clients.MayaHeaders.getxTokenHeader;


@Service
public class AccessClientImpl implements AccessClient {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    public AccessClientImpl(MayaProperties mayaProperties,
                            OAuth2RestTemplate oAuth2RestTemplate) {
        this.mayaProperties = mayaProperties;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    @Override
    public ResponseEntity<BaseResponse> getAccessToken(String xToken) {
        try {
            return oAuth2RestTemplate.exchange(buildAccessTokenRequestEntity(xToken), BaseResponse.class);
        } catch (Exception e) {
            throw e;
        }
    }

    private RequestEntity<Void> buildAccessTokenRequestEntity(String xToken) {
        return RequestEntity.post(URI.create(mayaProperties.getAccessTokenUrl()))
                .header(getxTokenHeader(), xToken)
                .build();
    }

}