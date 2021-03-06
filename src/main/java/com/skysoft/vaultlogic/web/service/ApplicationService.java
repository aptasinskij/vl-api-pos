package com.skysoft.vaultlogic.web.service;

import com.skysoft.vaultlogic.common.domain.application.Application;
import com.skysoft.vaultlogic.common.domain.application.projections.SmartContractApplication;

import java.net.URI;

public interface ApplicationService {

    Application registerApplication(Application application);

    Application findByName(String name);

    SmartContractApplication findSCProjectionByName(String name);

    URI getApplicationUriIfEnabled(Long appId);

}
