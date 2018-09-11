package com.skysoft.vaultlogic.web.service;

import com.skysoft.vaultlogic.common.domain.session.Session;

public interface SessionService {

    Session createApplicationSession(Long appId, String xToken);

    void closeSession(String xToken);

}
