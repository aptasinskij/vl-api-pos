package com.skysoft.vaultlogic.services;

import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import com.skysoft.vaultlogic.common.domain.kiosk.Kiosk;
import com.skysoft.vaultlogic.common.domain.kiosk.KioskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;

@Service
@Profile("cloud-quorum")
public class KioskServiceImpl implements KioskService {

    private static final String URL_FORMAT = "%s%s";

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate restTemplate;
    private final KioskRepository kioskRepository;

    @Autowired
    public KioskServiceImpl(MayaProperties mayaProperties, OAuth2RestTemplate restTemplate, KioskRepository kioskRepository) {
        this.mayaProperties = mayaProperties;
        this.restTemplate = restTemplate;
        this.kioskRepository = kioskRepository;
    }

    @Override
    @Transactional
    //fixme: USE KioskClient implementation to retrieve current session deviceInfo and custom mapper to map from DeviceInfo to Kiosk
    public Kiosk resolveKioskForSession(String xToken) {
        RequestEntity<Void> request = RequestEntity.post(deviceInfoURI()).header("X-Token", xToken).build();
        DeviceInfo deviceInfo = restTemplate.exchange(request, DeviceInfo.class).getBody();
        return kioskRepository.findByShortId(deviceInfo.shortId).orElse(kioskRepository.save(fromDeviceInfoResponse(deviceInfo)));
    }

    private Kiosk fromDeviceInfoResponse(DeviceInfo deviceInfo) {
        return Kiosk.kiosk(deviceInfo.shortId, deviceInfo.formattedAddress, deviceInfo.info, deviceInfo.timezone);
    }

    private URI deviceInfoURI() {
        return URI.create(String.format(URL_FORMAT, mayaProperties.getBaseUrl(), mayaProperties.getDevice().getInfo()));
    }

}
