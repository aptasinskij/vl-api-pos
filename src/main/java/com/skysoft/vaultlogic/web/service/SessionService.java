package com.skysoft.vaultlogic.web.service;

import com.skysoft.vaultlogic.common.domain.session.Session;

import java.math.BigInteger;

public interface SessionService {

    Session createApplicationSession(Long appId, String xToken);

    void closeSession(BigInteger sessionId);

}
