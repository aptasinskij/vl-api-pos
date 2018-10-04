package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.StatusCode;

public interface KioskApplication {

    StatusCode launchApplication(String xToken);

    StatusCode keepAlive(String xToken, String keepAliveToken);

    StatusCode clientActivity(String xToken);

    StatusCode closeApplication(String xToken);

}