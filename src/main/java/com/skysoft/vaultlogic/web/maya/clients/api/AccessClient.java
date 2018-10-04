package com.skysoft.vaultlogic.web.maya.clients.api;

import com.skysoft.vaultlogic.web.maya.clients.responces.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface AccessClient {


//    Headers:
//    Authorization: Basic <base64('clientid:clientsecret')>
//
//    Body:
//    grant_type=client_credentials

    // TODO: 03.10.18 add response
    ResponseEntity<BaseResponse> getAccessToken(String xToken);

}