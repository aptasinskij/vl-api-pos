package com.skysoft.vaultlogic.web.maya.clients.api;

import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface ApplicationClient {

    ResponseEntity<BaseResponse> launchApplication(String xToken);

    ResponseEntity<BaseResponse> keepAlive(String xToken, String keepAliveToken);

    ResponseEntity<BaseResponse> clientActivity(String xToken);

    ResponseEntity<BaseResponse> closeApplication(String xToken);

}