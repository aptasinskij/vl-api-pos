package com.skysoft.vaultlogic.web.maya.clients.api;

import com.skysoft.vaultlogic.web.maya.MayaException;
import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import com.skysoft.vaultlogic.web.maya.clients.responce.generalInfo.GetAllLocationsAndDevicesResponse;
import org.springframework.http.ResponseEntity;

public interface GeneralInfoClient {

    ResponseEntity<GetAllLocationsAndDevicesResponse> getLocationAndDevices(String xToken) throws MayaException;

    // TODO: 01.10.18 finished response
    ResponseEntity<BaseResponse> getDevice(String xToken);

    // TODO: 01.10.18 finished response
    ResponseEntity<BaseResponse> getDevices(String xToken);

}