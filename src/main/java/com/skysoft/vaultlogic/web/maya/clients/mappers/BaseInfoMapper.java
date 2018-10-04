package com.skysoft.vaultlogic.web.maya.clients.mappers;

import com.skysoft.vaultlogic.web.maya.clients.responseModels.BaseInfo;
import com.skysoft.vaultlogic.web.maya.clients.responces.BaseResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaseInfoMapper {

    BaseInfo responseToBaseInfo(BaseResponse response);

    BaseResponse infoToBaseResponse(BaseInfo info);

}