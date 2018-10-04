package com.skysoft.vaultlogic.clients.api.device;

import com.skysoft.vaultlogic.clients.requestModels.cameraDevice.StartPreviewBody;
import com.skysoft.vaultlogic.clients.requestModels.cameraDevice.TakeScanBody;
import com.skysoft.vaultlogic.clients.responseModels.BaseInfo;
import com.skysoft.vaultlogic.clients.responseModels.device.camera.StartPreviewInfo;
import com.skysoft.vaultlogic.clients.responseModels.device.camera.TakePhotoInfo;
import com.skysoft.vaultlogic.clients.responseModels.device.camera.TakeScanInfo;

public interface CameraDeviceClient {

    TakePhotoInfo takePhoto(String xToken);

    TakeScanInfo takeScan(String xToken, TakeScanBody takeScan);

    StartPreviewInfo startPreview(String xToken, StartPreviewBody startPreview);

    BaseInfo stopPreview(String xToken);

}