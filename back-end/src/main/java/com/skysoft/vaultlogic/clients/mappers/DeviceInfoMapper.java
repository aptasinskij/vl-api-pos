package com.skysoft.vaultlogic.clients.mappers;

import com.skysoft.vaultlogic.clients.responces.device.DeviceInfoResponse;
import com.skysoft.vaultlogic.clients.responseModels.device.DeviceInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeviceInfoMapper {

    DeviceInfo responseToDeviceInfo(DeviceInfoResponse response);

    DeviceInfoResponse infoToDeviceInfoResponse(DeviceInfo info);

}