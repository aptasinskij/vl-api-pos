package com.skysoft.vaultlogic.services;

import com.skysoft.vaultlogic.common.domain.application.Application;

import java.math.BigInteger;
import java.net.URI;

public interface ApplicationService {

    Application registerApplication(Application application);

    Application findByName(String name);

    URI getApplicationUri(BigInteger applicationId);

}
