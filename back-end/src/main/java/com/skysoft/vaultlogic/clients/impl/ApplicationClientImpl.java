package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.ApplicationClient;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import com.skysoft.vaultlogic.clients.mappers.BaseInfoMapper;
import com.skysoft.vaultlogic.clients.responseModels.BaseInfo;
import com.skysoft.vaultlogic.clients.requests.KeepAliveRequest;
import com.skysoft.vaultlogic.clients.responces.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.clients.MayaHeaders.getxTokenHeader;


@Service
public class ApplicationClientImpl implements ApplicationClient {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;
    private final BaseInfoMapper baseInfoMapper;

    @Autowired
    public ApplicationClientImpl(MayaProperties mayaProperties,
                                 OAuth2RestTemplate oAuth2RestTemplate,
                                 BaseInfoMapper baseInfoMapper) {
        this.mayaProperties = mayaProperties;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
        this.baseInfoMapper = baseInfoMapper;
    }

    @Override
    public BaseInfo launchApplication(String xToken) {
        try {
            ResponseEntity<BaseResponse> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getLaunchApplicationUrl()), BaseResponse.class);
            return baseInfoMapper.responseToBaseInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public BaseInfo keepAlive(String xToken, String keepAliveToken) {
        try {
            ResponseEntity<BaseResponse> exchange = oAuth2RestTemplate.exchange(buildKeepAliveRequestEntity(xToken, keepAliveToken), BaseResponse.class);
            return baseInfoMapper.responseToBaseInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public BaseInfo clientActivity(String xToken) {
        try {
            ResponseEntity<BaseResponse> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getClientActivityUrl()), BaseResponse.class);
            return baseInfoMapper.responseToBaseInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public BaseInfo closeApplication(String xToken) {
        try {
            ResponseEntity<BaseResponse> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getCloseApplicationUrl()), BaseResponse.class);
            return baseInfoMapper.responseToBaseInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
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