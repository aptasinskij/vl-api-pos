package com.skysoft.vaultlogic.clients.mappers.device;

import com.skysoft.vaultlogic.clients.responces.device.scanner.ScannerStatusResponse;
import com.skysoft.vaultlogic.clients.responseModels.device.scanner.ScannerStatusInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScannerDeviceMapper {

    ScannerStatusInfo responseToScannerStatusInfo(ScannerStatusResponse response);

    ScannerStatusResponse infoToScannerStatusResponse(ScannerStatusInfo info);

}