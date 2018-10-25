package com.skysoft.vaultlogic.services.cloud;

import com.skysoft.vaultlogic.clients.api.KioskApplicationClient;
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
@Profile("cloud")
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepo;
    private final ApplicationRepository applicationRepo;
    private final KioskService kioskService;
    private final KioskApplicationClient kioskApplicationClient;

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
        return applicationRepo.findById(applicationId)
                .flatMap(toApplicationAndResolvedKiosk(xToken))
                .map(buildSessionObject(xToken))
                .map(Session::markCreating)
                .map(sessionRepo::save)
                .map(appUrlSessionIdPair())
                .orElseThrow(RuntimeException::new);
    }

    private Function<Session, Pair<String, BigInteger>> appUrlSessionIdPair() {
        return session -> Pair.of(session.getApplication().getUri(), session.getId());
    }

    private Function<Pair<Application, Kiosk>, Session> buildSessionObject(String xToken) {
        return pair -> Session.session(pair.getFirst(), pair.getSecond(), xToken);
    }

    private Function<Application, Optional<Pair<Application, Kiosk>>> toApplicationAndResolvedKiosk(String xToken) {
        return application -> kioskService.resolveKioskForSession(xToken).map(toApplicationKioskPair(application));
    }

    private Function<Kiosk, Pair<Application, Kiosk>> toApplicationKioskPair(Application application) {
        return kiosk -> Pair.of(application, kiosk);
    }

}
