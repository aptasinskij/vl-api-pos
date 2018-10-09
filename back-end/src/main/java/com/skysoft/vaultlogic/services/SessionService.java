package com.skysoft.vaultlogic.services;

import com.skysoft.vaultlogic.common.domain.session.Session;
import org.springframework.data.util.Pair;

import java.math.BigInteger;

public interface SessionService {

    Session createApplicationSession(BigInteger applicationId, String xToken);

    void closeSession(BigInteger sessionId);

    String getSessionXToken(BigInteger sessionId);

    void activate(BigInteger sessionId);

    void failedToCreate(Session session);

    Pair<String, BigInteger> createSession(BigInteger applicationId, String xToken);

}
