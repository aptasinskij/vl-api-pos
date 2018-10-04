package com.skysoft.vaultlogic.web.maya.clients.mappers;

import com.skysoft.vaultlogic.web.maya.clients.responces.device.DeviceInfoResponse;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.device.DeviceInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeviceInfoMapper {

    DeviceInfo responseToDeviceInfo(DeviceInfoResponse response);

    DeviceInfoResponse infoToDeviceInfoResponse(DeviceInfo info);

}