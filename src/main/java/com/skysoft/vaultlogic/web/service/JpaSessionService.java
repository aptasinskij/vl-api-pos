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
public class JpaSessionService implements SessionService {

    private final SessionRepository sessionRepo;
    private final ApplicationRepository applicationRepo;

    @Autowired
    public JpaSessionService(SessionRepository sessionRepo, ApplicationRepository appRepo) {
        this.sessionRepo = sessionRepo;
        this.applicationRepo = appRepo;
    }

    @Override
    @Transactional
    public Session createApplicationSession(BigInteger applicationId, String xToken) {
        Application app = applicationRepo.getOne(applicationId);
        Session session = Session.newApplicationSession(app, xToken).markCreating();
        return sessionRepo.save(session);
    }

    @Override
    @Transactional
    public void closeSession(BigInteger sessionId) {
        sessionRepo.findById(sessionId).map(Session::markCloseRequested).ifPresent(sessionRepo::save);
    }

    @Override
    @Transactional(readOnly = true)
    public String getSessionXToken(BigInteger sessionId) {
        return sessionRepo.findSessionXTokenById(sessionId).getXToken();
    }

    @Override
    @Transactional
    public void activate(Session session) {
        sessionRepo.save(session.markActive());
    }

    @Override
    @Transactional
    public void failedToCreate(Session session) {
        sessionRepo.save(session.markFailedToCreate());
    }

}
