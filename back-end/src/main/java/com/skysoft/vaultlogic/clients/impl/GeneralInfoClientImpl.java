package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import com.skysoft.vaultlogic.web.maya.clients.api.GeneralInfoClient;
import com.skysoft.vaultlogic.clients.mappers.DeviceInfoMapper;
import com.skysoft.vaultlogic.clients.mappers.GeneralInfoMapper;
import com.skysoft.vaultlogic.clients.requests.GetDeviceInfoRequest;
import com.skysoft.vaultlogic.clients.responces.device.DeviceInfoResponse;
import com.skysoft.vaultlogic.clients.responces.general.AllLocationsAndDevicesResponse;
import com.skysoft.vaultlogic.clients.responces.general.DevicesInfoResponse;
import com.skysoft.vaultlogic.clients.responseModels.device.DeviceInfo;
import com.skysoft.vaultlogic.clients.responseModels.general.AllLocationsAndDevicesInfo;
import com.skysoft.vaultlogic.clients.responseModels.general.DevicesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.web.maya.MayaHeaders.getxTokenHeader;

@Service
public class GeneralInfoClientImpl implements GeneralInfoClient {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;
    private final DeviceInfoMapper deviceInfoMapper;
    private final GeneralInfoMapper generalInfoMapper;

    @Autowired
    public GeneralInfoClientImpl(MayaProperties mayaProperties,
                                 OAuth2RestTemplate oAuth2RestTemplate,
                                 DeviceInfoMapper deviceInfoMapper,
                                 GeneralInfoMapper generalInfoMapper) {
        this.mayaProperties = mayaProperties;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
        this.deviceInfoMapper = deviceInfoMapper;
        this.generalInfoMapper = generalInfoMapper;
    }

    @Override
    public AllLocationsAndDevicesInfo getLocationAndDevices(String xToken) {
        try {
            ResponseEntity<AllLocationsAndDevicesResponse> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getLocationAndDevicesUrl()), AllLocationsAndDevicesResponse.class);
            return generalInfoMapper.responseToAllLocationsAndDevicesInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public DeviceInfo getGeneralDeviceInfo(String xToken, String deviceId) {
        try {
            ResponseEntity<DeviceInfoResponse> exchange = oAuth2RestTemplate.exchange(buildGetDeviceInfoRequestEntity(xToken, deviceId), DeviceInfoResponse.class);
            return deviceInfoMapper.responseToDeviceInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public DevicesInfo getGeneralDevicesInfo(String xToken) {
        try {
            ResponseEntity<DevicesInfoResponse> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getDevicesUrl()), DevicesInfoResponse.class);
            return generalInfoMapper.responseToDevicesInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(getxTokenHeader(), xToken)
                .build();
    }

    private RequestEntity<GetDeviceInfoRequest> buildGetDeviceInfoRequestEntity(String xToken, String deviceId) {
        return RequestEntity.post(URI.create(mayaProperties.getDeviceUrl()))
                .header(getxTokenHeader(), xToken)
                .body(GetDeviceInfoRequest.of(deviceId));
    }

}