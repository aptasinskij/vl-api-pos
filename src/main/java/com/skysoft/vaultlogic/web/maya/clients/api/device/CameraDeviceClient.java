package com.skysoft.vaultlogic.web.maya.clients.api.device;

import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import com.skysoft.vaultlogic.web.maya.clients.responce.device.camera.StartPreviewResponse;
import com.skysoft.vaultlogic.web.maya.clients.responce.device.camera.TakePhotoResponse;
import com.skysoft.vaultlogic.web.maya.clients.responce.device.camera.TakeScanResponse;
import org.springframework.http.ResponseEntity;

public interface CameraDeviceClient {

    ResponseEntity<TakePhotoResponse> takePhoto(String xToken);

    ResponseEntity<TakeScanResponse> takeScan(String xToken);

    ResponseEntity<StartPreviewResponse> startPreview(String xToken);

    ResponseEntity<BaseResponse> stopPreview(String xToken);

}