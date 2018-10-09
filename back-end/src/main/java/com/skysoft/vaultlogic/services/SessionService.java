package com.skysoft.vaultlogic.services;

import com.skysoft.vaultlogic.common.domain.session.Session;
import org.springframework.data.util.Pair;

import java.math.BigInteger;

public interface SessionService {

    void closeSession(BigInteger sessionId);

    void activate(BigInteger sessionId);

    Pair<String, BigInteger> createSession(BigInteger applicationId, String xToken);

}
