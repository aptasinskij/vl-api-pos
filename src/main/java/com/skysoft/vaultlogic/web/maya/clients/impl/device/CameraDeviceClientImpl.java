package com.skysoft.vaultlogic.web.maya.clients.impl.device;

import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import com.skysoft.vaultlogic.web.maya.clients.api.device.CameraDeviceClient;
import com.skysoft.vaultlogic.web.maya.clients.mappers.BaseInfoMapper;
import com.skysoft.vaultlogic.web.maya.clients.mappers.device.CameraDeviceMapper;
import com.skysoft.vaultlogic.web.maya.clients.requestModels.cameraDevice.StartPreviewBody;
import com.skysoft.vaultlogic.web.maya.clients.requestModels.cameraDevice.TakeScanBody;
import com.skysoft.vaultlogic.web.maya.clients.requests.cameraDevice.StartPreviewRequest;
import com.skysoft.vaultlogic.web.maya.clients.requests.cameraDevice.TakeScanRequest;
import com.skysoft.vaultlogic.web.maya.clients.responces.BaseResponse;
import com.skysoft.vaultlogic.web.maya.clients.responces.device.camera.StartPreviewResponse;
import com.skysoft.vaultlogic.web.maya.clients.responces.device.camera.TakePhotoResponse;
import com.skysoft.vaultlogic.web.maya.clients.responces.device.camera.TakeScanResponse;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.BaseInfo;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.device.camera.StartPreviewInfo;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.device.camera.TakePhotoInfo;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.device.camera.TakeScanInfo;
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
    private final BaseInfoMapper baseInfoMapper;
    private final CameraDeviceMapper cameraMapper;

    @Autowired
    public CameraDeviceClientImpl(MayaProperties mayaProperties,
                                  OAuth2RestTemplate oAuth2RestTemplate,
                                  BaseInfoMapper baseInfoMapper,
                                  CameraDeviceMapper cameraMapper) {
        this.mayaProperties = mayaProperties;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
        this.baseInfoMapper = baseInfoMapper;
        this.cameraMapper = cameraMapper;
    }

    @Override
    public TakePhotoInfo takePhoto(String xToken) {
        try {
            ResponseEntity<TakePhotoResponse> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getTakePhotoUrl()), TakePhotoResponse.class);
            return cameraMapper.responseToTakePhotoInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public TakeScanInfo takeScan(String xToken, TakeScanBody takeScan) {
        try {
            ResponseEntity<TakeScanResponse> exchange = oAuth2RestTemplate.exchange(buildTakeScanRequestEntity(xToken, takeScan), TakeScanResponse.class);
            return cameraMapper.responseToTakeScanInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public StartPreviewInfo startPreview(String xToken, StartPreviewBody startPreview) {
        try {
            ResponseEntity<StartPreviewResponse> exchange = oAuth2RestTemplate.exchange(buildStartPreviewRequestEntity(xToken, startPreview), StartPreviewResponse.class);
            return cameraMapper.responseToStartPreviewInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public BaseInfo stopPreview(String xToken) {
        try {
            ResponseEntity<BaseResponse> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getStopPreviewUrl()), BaseResponse.class);
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

    private RequestEntity<TakeScanRequest> buildTakeScanRequestEntity(String xToken, TakeScanBody takeScan) {
        return RequestEntity.post(URI.create(mayaProperties.getTakeScanUrl()))
                .header(getxTokenHeader(), xToken)
                .body(TakeScanRequest.of(takeScan));
    }

    private RequestEntity<StartPreviewRequest> buildStartPreviewRequestEntity(String xToken, StartPreviewBody startPreview) {
        return RequestEntity.post(URI.create(mayaProperties.getStartPreviewUrl()))
                .header(getxTokenHeader(), xToken)
                .body(StartPreviewRequest.of(startPreview));
    }

}