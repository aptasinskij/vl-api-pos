package com.skysoft.vaultlogic.services;

import com.skysoft.vaultlogic.common.domain.application.Application;
import com.skysoft.vaultlogic.common.domain.application.ApplicationRepository;
import com.skysoft.vaultlogic.common.domain.kiosk.Kiosk;
import com.skysoft.vaultlogic.common.domain.session.Session;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
@AllArgsConstructor
public class JpaSessionService implements SessionService {

    private final SessionRepository sessionRepo;
    private final ApplicationRepository applicationRepo;
    private final KioskService kioskService;

    @Override
    @Transactional
    public Session createApplicationSession(BigInteger applicationId, String xToken) {
        Application app = applicationRepo.getOne(applicationId);
        Kiosk kiosk = kioskService.resolveKioskForSession(xToken).orElseThrow(RuntimeException::new);
        Session session = Session.session(app, kiosk, xToken).markCreating();
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
    public void activate(BigInteger sessionId) {
        Session session = sessionRepo.getOne(sessionId).markActive();
        sessionRepo.save(session);
    }

    @Override
    @Transactional
    public void failedToCreate(Session session) {
        sessionRepo.save(session.markFailedToCreate());
    }

    @Override
    @Transactional
    public Pair<String, BigInteger> createSession(BigInteger applicationId, String xToken) {
        Kiosk kiosk = kioskService.resolveKioskForSession(xToken).orElseThrow(RuntimeException::new);
        Application application = applicationRepo.findById(applicationId).orElseThrow(RuntimeException::new);
        Session session = sessionRepo.save(Session.session(application, kiosk, xToken).markCreating());
        return Pair.of(application.getUri(), session.getId());
    }

}
