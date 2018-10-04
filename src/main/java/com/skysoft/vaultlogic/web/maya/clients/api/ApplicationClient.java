package com.skysoft.vaultlogic.web.maya.clients.api;

import com.skysoft.vaultlogic.web.maya.clients.responseModels.BaseInfo;

public interface ApplicationClient {

    BaseInfo launchApplication(String xToken);

    BaseInfo keepAlive(String xToken, String keepAliveToken);

    BaseInfo clientActivity(String xToken);

    BaseInfo closeApplication(String xToken);

}