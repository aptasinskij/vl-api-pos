package com.skysoft.vaultlogic.clients.mappers;

import com.skysoft.vaultlogic.clients.responseModels.BaseInfo;
import com.skysoft.vaultlogic.clients.responces.BaseResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaseInfoMapper {

    BaseInfo responseToBaseInfo(BaseResponse response);

    BaseResponse infoToBaseResponse(BaseInfo info);

}