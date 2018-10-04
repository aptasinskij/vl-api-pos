package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.MayaGeneralInfo;
import com.skysoft.vaultlogic.clients.api.model.KioskDevice;
import com.skysoft.vaultlogic.clients.api.model.KioskId;
import com.skysoft.vaultlogic.clients.api.model.KiosksInfo;
import com.skysoft.vaultlogic.clients.api.model.LocationsAndDevices;
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
public class MayaGeneralInfoHttpClient implements MayaGeneralInfo {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    @Override
    public LocationsAndDevices getLocationAndDevices(String xToken) {
        try {
            ResponseEntity<LocationsAndDevices> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getLocationAndDevicesUrl()), LocationsAndDevices.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public KioskDevice getDevice(String deviceId) {
//        try {
//            ResponseEntity<DeviceInfoResponse> exchange = oAuth2RestTemplate.exchange(buildGetDeviceInfoRequestEntity(xToken, deviceId), DeviceInfoResponse.class);
//            return deviceInfoMapper.responseToDeviceInfo(exchange.getBody());
//        } catch (Exception e) {
//            throw e;
//        }
        return null;
    }

    @Override
    public KiosksInfo getDevices() {
        return null;
        /*try {
            ResponseEntity<DevicesInfoResponse> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getDevicesUrl()), DevicesInfoResponse.class);
            return generalInfoMapper.responseToDevicesInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }*/
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(X_TOKEN_HEADER, xToken)
                .build();
    }

    private RequestEntity<KioskId> buildGetDeviceInfoRequestEntity(String xToken, String deviceId) {
        return RequestEntity.post(URI.create(mayaProperties.getDeviceUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(KioskId.of(deviceId));
    }

}