package com.skysoft.vaultlogic.web.maya.clients.mappers.device;

import com.skysoft.vaultlogic.web.maya.clients.responces.device.scanner.ScannerStatusResponse;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.device.scanner.ScannerStatusInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScannerDeviceMapper {

    ScannerStatusInfo responseToScannerStatusInfo(ScannerStatusResponse response);

    ScannerStatusResponse infoToScannerStatusResponse(ScannerStatusInfo info);

}