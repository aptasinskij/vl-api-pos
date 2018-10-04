package com.skysoft.vaultlogic.clients.impl.device;

import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import com.skysoft.vaultlogic.web.maya.clients.api.device.ScannerDeviceClient;
import com.skysoft.vaultlogic.clients.mappers.device.ScannerDeviceMapper;
import com.skysoft.vaultlogic.clients.responces.device.scanner.ScannerStatusResponse;
import com.skysoft.vaultlogic.clients.responseModels.device.scanner.ScannerStatusInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.web.maya.MayaHeaders.getxTokenHeader;

@Service
public class ScannerDeviceClientImpl implements ScannerDeviceClient {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;
    private final ScannerDeviceMapper scannerMapper;

    @Autowired
    public ScannerDeviceClientImpl(MayaProperties mayaProperties,
                                   OAuth2RestTemplate oAuth2RestTemplate,
                                   ScannerDeviceMapper scannerMapper) {
        this.mayaProperties = mayaProperties;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
        this.scannerMapper = scannerMapper;
    }

    @Override
    public ScannerStatusInfo getScannerDeviceStatus(String xToken) {
        try {
            ResponseEntity<ScannerStatusResponse> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getScannerStatusUrl()), ScannerStatusResponse.class);
            return scannerMapper.responseToScannerStatusInfo(exchange.getBody());
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
