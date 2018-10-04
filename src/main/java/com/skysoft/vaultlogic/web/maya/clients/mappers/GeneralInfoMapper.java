package com.skysoft.vaultlogic.web.maya.clients.mappers;

import com.skysoft.vaultlogic.web.maya.clients.responces.general.AllLocationsAndDevicesResponse;
import com.skysoft.vaultlogic.web.maya.clients.responces.general.DevicesInfoResponse;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.general.AllLocationsAndDevicesInfo;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.general.DevicesInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneralInfoMapper {

    AllLocationsAndDevicesInfo responseToAllLocationsAndDevicesInfo(AllLocationsAndDevicesResponse response);

    AllLocationsAndDevicesResponse infoToAllLocationsAndDevicesResponse(AllLocationsAndDevicesInfo info);

    DevicesInfo responseToDevicesInfo(DevicesInfoResponse response);

    DevicesInfoResponse infoToDevicesInfoResponse(DevicesInfo info);

}