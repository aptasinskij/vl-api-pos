package com.skysoft.vaultlogic.listeners.cloud;

import com.skysoft.vaultlogic.clients.api.KioskApplicationClient;
import com.skysoft.vaultlogic.common.domain.session.Session;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.session.events.SessionActivated;
import com.skysoft.vaultlogic.common.domain.session.events.SessionCloseRequested;
import com.skysoft.vaultlogic.common.domain.session.events.SessionClosed;
import com.skysoft.vaultlogic.common.domain.session.events.SessionCreating;
import com.skysoft.vaultlogic.common.domain.session.projections.SessionId;
import com.skysoft.vaultlogic.contracts.SessionOracle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@Profile("cloud")
public class CloudSessionDomainEventsListener {

    private final SessionOracle sessionOracle;
    private final SessionRepository sessionRepository;
    private final KioskApplicationClient kioskApplicationClient;

    @Autowired
    public CloudSessionDomainEventsListener(SessionOracle sessionOracle, SessionRepository sessionRepository, KioskApplicationClient kioskApplicationClient) {
        this.sessionOracle = sessionOracle;
        this.sessionRepository = sessionRepository;
        this.kioskApplicationClient = kioskApplicationClient;
    }

    @Async
    @TransactionalEventListener
    @Transactional(readOnly = true)
    public void creating(SessionCreating event) {
        log.info("[x]---> Session creating with XToken: {}", event.xToken);
        Session session = sessionRepository.findByXTokenJoinApplicationAndKiosk(event.xToken).orElseThrow(RuntimeException::new);
        sessionOracle.createSession(
                session.getId(),
                session.getApplication().getId(),
                session.getXToken(),
                session.getKiosk().getShortId()
        ).observable().subscribe(
                tx -> log.info("[x] session created in smart contract: {}", tx.getTransactionHash()),
                error -> log.error("[x] error create session in smart contract: {}", error.getMessage())
        );
        kioskApplicationClient.launchApplication(event.xToken)
                .onFailure(throwable -> log.error("[x] failed to launch application: {}", throwable.getMessage()))
                .onSuccess(statusCode -> log.info("[x] successfully launched application: {}", statusCode));
    }

    @Async
    @TransactionalEventListener
    @Transactional(readOnly = true)
    public void activated(SessionActivated event) {
        log.info("[x]---> Session activated with XToken: {}", event.xToken);
        SessionId session = sessionRepository.findSessionIdByXToken(event.xToken);
        sessionOracle.activate(session.getId()).observable().take(1).subscribe(
                tx -> log.info("[x] Saved to Smart Contract. TX: {}", tx.getTransactionHash()),
                throwable -> log.error("[x] Failed to save", throwable)
        );
    }

    @Async
    @TransactionalEventListener
    public void closeRequested(SessionCloseRequested event) {
        log.info("[x]---> Sending CLOSE APPLICATION MAYA REQUEST");
        kioskApplicationClient.closeApplication(event.xToken)
                .onFailure(throwable -> log.error("[x] failed to send close application: {}", throwable.getMessage()))
                .onSuccess(statusCode -> log.info("[x] successfully send close application")).toJavaOptional()
                .flatMap(statusCode -> sessionRepository.findByXToken(event.xToken))
                .map(Session::markClosed)
                .ifPresent(sessionRepository::save);
    }

    @Async
    @TransactionalEventListener
    @Transactional(readOnly = true)
    public void closed(SessionClosed event) {
        log.info("[x]---> Session CLOSED EVENT HANDLING");
        SessionId sessionId = sessionRepository.findSessionIdByXToken(event.xToken);
        sessionOracle.successClose(sessionId.getId()).observable().take(1).subscribe(
                transactionReceipt -> log.info("[x] confirmed session close to smart contract"),
                throwable -> log.error("[x] failed to confirm session fail close to smart contract")
        );
    }

}
