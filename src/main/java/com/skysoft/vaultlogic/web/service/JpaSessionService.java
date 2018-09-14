package com.skysoft.vaultlogic.web.service;

import com.skysoft.vaultlogic.common.domain.application.Application;
import com.skysoft.vaultlogic.common.domain.application.ApplicationRepository;
import com.skysoft.vaultlogic.common.domain.session.Session;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
public class JpaSessionService implements SessionService{

    private final SessionRepository sessionRepo;
    private final ApplicationRepository applicationRepo;

    @Autowired
    public JpaSessionService(SessionRepository sessionRepo, ApplicationRepository appRepo) {
        this.sessionRepo = sessionRepo;
        this.applicationRepo = appRepo;
    }

    @Override
    @Transactional
    public Session createApplicationSession(Long appId, String xToken) {
        Application app = applicationRepo.getOne(appId);
        Session session = Session.newApplicationSession(app, xToken).markActive();
        return sessionRepo.save(session);
    }

    @Override
    @Transactional
    public void closeSession(BigInteger sessionId) {
        Session session = sessionRepo.getOne(sessionId.longValue());
        session.markCloseRequested();
        sessionRepo.save(session);
    }

}
