package com.skysoft.vaultlogic.web.maya.clients.impl;

import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import com.skysoft.vaultlogic.web.maya.clients.api.ApplicationClient;
import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import com.skysoft.vaultlogic.web.controller.cloud.KeepAliveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.web.maya.MayaHeaders.getxTokenHeader;

@Service
public class ApplicationClientImpl implements ApplicationClient {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    public ApplicationClientImpl(MayaProperties mayaProperties,
                                 OAuth2RestTemplate oAuth2RestTemplate) {
        this.mayaProperties = mayaProperties;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    @Override
    public ResponseEntity<BaseResponse> launchApplication(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getLaunchApplicationUrl()), BaseResponse.class);
    }

    @Override
    public ResponseEntity<BaseResponse> keepAlive(String xToken, String keepAliveToken) {
        return oAuth2RestTemplate.exchange(buildKeepAliveRequestEntity(xToken, keepAliveToken), BaseResponse.class);
    }

    @Override
    public ResponseEntity<BaseResponse> clientActivity(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getClientActivityUrl()), BaseResponse.class);
    }

    @Override
    public ResponseEntity<BaseResponse> closeApplication(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getCloseApplicationUrl()), BaseResponse.class);
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(getxTokenHeader(), xToken)
                .build();
    }

    private RequestEntity<KeepAliveRequest> buildKeepAliveRequestEntity(String xToken, String keepAliveToken) {
        return RequestEntity.post(URI.create(mayaProperties.getKeepAliveUrl()))
                .header(getxTokenHeader(), xToken)
                .body(KeepAliveRequest.of(keepAliveToken));
    }

}