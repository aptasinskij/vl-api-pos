package com.skysoft.vaultlogic.clients.impl.device;

import com.skysoft.vaultlogic.clients.api.device.DeviceInfoClient;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import com.skysoft.vaultlogic.clients.mappers.DeviceInfoMapper;
import com.skysoft.vaultlogic.clients.responces.device.DeviceInfoResponse;
import com.skysoft.vaultlogic.clients.responseModels.device.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.clients.MayaHeaders.getxTokenHeader;


@Service
public class DeviceInfoClientImpl implements DeviceInfoClient {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;
    private final DeviceInfoMapper deviceInfoMapper;

    @Autowired
    public DeviceInfoClientImpl(MayaProperties mayaProperties,
                                OAuth2RestTemplate oAuth2RestTemplate,
                                DeviceInfoMapper deviceInfoMapper) {
        this.mayaProperties = mayaProperties;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
        this.deviceInfoMapper = deviceInfoMapper;
    }

    @Override
    public DeviceInfo getDeviceInfo(String xToken) {
        try {
            ResponseEntity<DeviceInfoResponse> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getDeviceInfoUrl()), DeviceInfoResponse.class);
            return deviceInfoMapper.responseToDeviceInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(getxTokenHeader(), xToken)
                .build();
    }

}