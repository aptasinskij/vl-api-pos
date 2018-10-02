package com.skysoft.vaultlogic.web.maya.clients.impl.device;

import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import com.skysoft.vaultlogic.web.maya.clients.api.device.DeviceInfoClient;
import com.skysoft.vaultlogic.web.maya.clients.responce.device.DeviceInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.web.maya.MayaHeaders.getxTokenHeader;

@Service
public class DeviceInfoClientImpl implements DeviceInfoClient {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    public DeviceInfoClientImpl(MayaProperties mayaProperties,
                                OAuth2RestTemplate oAuth2RestTemplate) {
        this.mayaProperties = mayaProperties;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    @Override
    public ResponseEntity<DeviceInfoResponse> getDeviceInfo(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getDeviceInfoUrl()), DeviceInfoResponse.class);
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(getxTokenHeader(), xToken)
                .build();
    }

}