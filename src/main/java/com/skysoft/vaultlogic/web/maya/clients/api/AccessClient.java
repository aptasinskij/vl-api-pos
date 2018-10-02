package com.skysoft.vaultlogic.web.maya.clients.api;

import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface AccessClient {

    // TODO: 01.10.18 add realisation and response
    ResponseEntity<BaseResponse> getAccessToken(String xToken);

}