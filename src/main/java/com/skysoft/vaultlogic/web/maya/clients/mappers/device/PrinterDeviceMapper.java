package com.skysoft.vaultlogic.web.maya.clients.mappers.device;

import com.skysoft.vaultlogic.web.maya.clients.responces.device.printer.CreateReceiptUrlResponse;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.device.printer.CreateReceiptUrlInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrinterDeviceMapper {

    CreateReceiptUrlInfo responseToCreateReceiptUrlInfo(CreateReceiptUrlResponse response);

    CreateReceiptUrlResponse infoToCreateReceiptUrlResponse(CreateReceiptUrlInfo info);

}