package com.skysoft.vaultlogic.services.quorum;

import com.skysoft.vaultlogic.clients.api.KioskApplication;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
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
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
@Profile("quorum")
@AllArgsConstructor
public class QuorumSessionService implements SessionService {

    private final SessionRepository sessionRepo;
    private final ApplicationRepository applicationRepo;
    private final KioskService kioskService;
    private final KioskApplication kioskApplication;

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
        return kioskApplication.launchApplication(xToken).toJavaOptional()
                .flatMap(resolveKiosk(xToken))
                .flatMap(appAndKioskPair(applicationId))
                .map(sessionObject(xToken))
                .map(Session::markCreating)
                .map(sessionRepo::save)
                .map(this::applicationUrlAndSessionId)
                .orElseThrow(RuntimeException::new);
    }

    private Function<Kiosk, Optional<Pair<Application, Kiosk>>> appAndKioskPair(BigInteger applicationId) {
        return kiosk -> applicationRepo.findById(applicationId).map(app -> Pair.of(app, kiosk));
    }

    private Function<StatusCode, Optional<Kiosk>> resolveKiosk(String xToken) {
        return ignore -> kioskService.resolveKioskForSession(xToken);
    }

    private Function<Pair<Application, Kiosk>, Session> sessionObject(String xToken) {
        return pair -> Session.session(pair.getFirst(), pair.getSecond(), xToken);
    }

    private Pair<String, BigInteger> applicationUrlAndSessionId(Session session) {
        return Pair.of(session.getApplication().getUri(), session.getId());
    }

}
