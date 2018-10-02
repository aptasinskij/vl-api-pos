package com.skysoft.vaultlogic.web.maya.clients.impl;

import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import com.skysoft.vaultlogic.web.maya.MayaException;
import com.skysoft.vaultlogic.web.maya.MayaStatus;
import com.skysoft.vaultlogic.web.maya.clients.api.GeneralInfoClient;
import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import com.skysoft.vaultlogic.web.maya.clients.responce.generalInfo.GetAllLocationsAndDevicesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.EmptyStackException;

import static com.skysoft.vaultlogic.web.maya.MayaHeaders.getxTokenHeader;
import static com.skysoft.vaultlogic.web.maya.MayaStatus.CORRECT_CODE;

@Service
public class GeneralInfoClientImpl implements GeneralInfoClient {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    public GeneralInfoClientImpl(MayaProperties mayaProperties,
                                 OAuth2RestTemplate oAuth2RestTemplate) {
        this.mayaProperties = mayaProperties;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    @Override
    public ResponseEntity<GetAllLocationsAndDevicesResponse> getLocationAndDevices(String xToken) throws MayaException {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getLocationAndDevicesUrl()), GetAllLocationsAndDevicesResponse.class);
    }

    @Override
    public ResponseEntity<BaseResponse> getDevice(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getDeviceUrl()), BaseResponse.class);
    }

    @Override
    public ResponseEntity<BaseResponse> getDevices(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getDevicesUrl()), BaseResponse.class);
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(getxTokenHeader(), xToken)
                .build();
    }

    public boolean getStatus(ResponseEntity<GetAllLocationsAndDevicesResponse> exchange) {
        return MayaStatus.getStatus(exchange.getBody().getErrorCode()).equals(CORRECT_CODE);
    }

}