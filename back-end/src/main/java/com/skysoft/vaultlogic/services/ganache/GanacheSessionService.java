package com.skysoft.vaultlogic.services.ganache;

import com.skysoft.vaultlogic.common.domain.application.Application;
import com.skysoft.vaultlogic.common.domain.application.ApplicationRepository;
import com.skysoft.vaultlogic.common.domain.kiosk.Kiosk;
import com.skysoft.vaultlogic.common.domain.session.Session;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.services.KioskService;
import com.skysoft.vaultlogic.services.SessionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Slf4j
@Service
@AllArgsConstructor
@Profile("ganache")
public class GanacheSessionService implements SessionService {

    private final SessionRepository sessionRepo;
    private final ApplicationRepository applicationRepo;
    private final KioskService kioskService;

    @Override
    @Transactional
    public void closeSession(BigInteger sessionId) {
        sessionRepo.findById(sessionId).map(Session::markCloseRequested).ifPresent(sessionRepo::save);
    }

    @Override
    @Transactional
    public void activate(BigInteger sessionId) {
        Session session = sessionRepo.getOne(sessionId).markActive();
        sessionRepo.save(session);
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
