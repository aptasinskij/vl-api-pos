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
    public LocationsAndDevices getLocationAndDevices() {
        try {
            ResponseEntity<LocationsAndDevices> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(mayaProperties.getLocationAndDevicesUrl()), LocationsAndDevices.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public KioskDevice getDevice(String deviceId) {
        try {
            ResponseEntity<KioskDevice> exchange = oAuth2RestTemplate.exchange(buildGetDeviceInfoRequestEntity(deviceId), KioskDevice.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public KiosksInfo getDevices() {
        try {
            ResponseEntity<KiosksInfo> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(mayaProperties.getDevicesUrl()), KiosksInfo.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    private RequestEntity<Void> buildRequestEntity(String url) {
        return RequestEntity.post(URI.create(url))
                .build();
    }

    private RequestEntity<KioskId> buildGetDeviceInfoRequestEntity(String deviceId) {
        return RequestEntity.post(URI.create(mayaProperties.getDeviceUrl()))
                .body(KioskId.of(deviceId));
    }

}