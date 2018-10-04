package com.skysoft.vaultlogic.web.maya.clients.api.device;

import com.skysoft.vaultlogic.web.maya.clients.requestModels.cameraDevice.StartPreviewBody;
import com.skysoft.vaultlogic.web.maya.clients.requestModels.cameraDevice.TakeScanBody;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.BaseInfo;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.device.camera.StartPreviewInfo;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.device.camera.TakePhotoInfo;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.device.camera.TakeScanInfo;

public interface CameraDeviceClient {

    TakePhotoInfo takePhoto(String xToken);

    TakeScanInfo takeScan(String xToken, TakeScanBody takeScan);

    StartPreviewInfo startPreview(String xToken, StartPreviewBody startPreview);

    BaseInfo stopPreview(String xToken);

}