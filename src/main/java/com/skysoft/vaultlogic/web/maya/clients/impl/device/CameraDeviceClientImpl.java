package com.skysoft.vaultlogic.web.maya.clients.impl.device;

import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import com.skysoft.vaultlogic.web.maya.clients.api.device.CameraDeviceClient;
import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import com.skysoft.vaultlogic.web.maya.clients.responce.device.camera.StartPreviewResponse;
import com.skysoft.vaultlogic.web.maya.clients.responce.device.camera.TakePhotoResponse;
import com.skysoft.vaultlogic.web.maya.clients.responce.device.camera.TakeScanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.web.maya.MayaHeaders.getxTokenHeader;

@Service
public class CameraDeviceClientImpl implements CameraDeviceClient {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    public CameraDeviceClientImpl(MayaProperties mayaProperties,
                                  OAuth2RestTemplate oAuth2RestTemplate) {
        this.mayaProperties = mayaProperties;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    @Override
    public ResponseEntity<TakePhotoResponse> takePhoto(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getTakePhotoUrl()), TakePhotoResponse.class);
    }

    @Override
    public ResponseEntity<TakeScanResponse> takeScan(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getTakeScanUrl()), TakeScanResponse.class);
    }

    @Override
    public ResponseEntity<StartPreviewResponse> startPreview(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getStartPreviewUrl()), StartPreviewResponse.class);
    }

    @Override
    public ResponseEntity<BaseResponse> stopPreview(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getStopPreviewUrl()), BaseResponse.class);
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(getxTokenHeader(), xToken)
                .build();
    }

}