package com.skysoft.vaultlogic.clients.mappers.device;

import com.skysoft.vaultlogic.clients.responces.device.camera.StartPreviewResponse;
import com.skysoft.vaultlogic.clients.responces.device.camera.TakePhotoResponse;
import com.skysoft.vaultlogic.clients.responces.device.camera.TakeScanResponse;
import com.skysoft.vaultlogic.clients.responseModels.device.camera.StartPreviewInfo;
import com.skysoft.vaultlogic.clients.responseModels.device.camera.TakePhotoInfo;
import com.skysoft.vaultlogic.clients.responseModels.device.camera.TakeScanInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CameraDeviceMapper {

    TakePhotoInfo responseToTakePhotoInfo(TakePhotoResponse response);

    TakePhotoResponse infoToTakePhotoResponse(TakePhotoInfo info);

    TakeScanInfo responseToTakeScanInfo(TakeScanResponse response);

    TakeScanResponse infoToTakeScanResponse(TakeScanInfo info);

    StartPreviewInfo responseToStartPreviewInfo(StartPreviewResponse response);

    StartPreviewResponse infoToStartPreviewResponse(StartPreviewInfo info);

}