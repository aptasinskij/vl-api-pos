package com.skysoft.vaultlogic.web.service;

import com.skysoft.vaultlogic.common.domain.session.Session;

import java.math.BigInteger;

public interface SessionService {

    Session createApplicationSession(BigInteger applicationId, String xToken);

    void closeSession(BigInteger sessionId);

    String getSessionXToken(BigInteger sessionId);

    void activate(Session session);

    void failedToCreate(Session session);
}
