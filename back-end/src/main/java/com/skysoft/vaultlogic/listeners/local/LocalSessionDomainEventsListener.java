package com.skysoft.vaultlogic.listeners.local;

import com.skysoft.vaultlogic.common.domain.session.Session;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.session.SessionTxLogRepository;
import com.skysoft.vaultlogic.common.domain.session.events.*;
import com.skysoft.vaultlogic.common.domain.session.projections.SessionId;
import com.skysoft.vaultlogic.contracts.SessionManager;
import com.skysoft.vaultlogic.listeners.RemoteRequestEmulator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import rx.functions.Action1;

@Slf4j
@Component
@Profile("ganache")
@AllArgsConstructor
public class LocalSessionDomainEventsListener {

    private final SessionManager sessionManager;
    private final SessionRepository sessionRepository;
    private final SessionTxLogRepository sessionTxLogRepository;

    private final Action1<TransactionReceipt> onSuccess = tx -> log.info("[x] Confirmed, TX: {}", tx.getTransactionHash());
    private final Action1<Throwable> onError = throwable -> log.error("[x] Error during confirmation", throwable);

    @Async
    @TransactionalEventListener
    @Transactional(readOnly = true)
    public void creating(SessionCreating event) {

        log.info("[x]---> Session creating with XToken: {}", event.xToken);
        Session session = sessionRepository.findByXTokenJoinApplicationAndKiosk(event.xToken).orElseThrow(RuntimeException::new);
        sessionManager.createSession(session.getId(), session.getApplication().getId(), session.getXToken(), session.getKiosk().getShortId())
                .observable().subscribe(onSuccess, onError);
    }

    @Async
    @TransactionalEventListener
    public void failedToCreate(SessionFailedToCreate event) {
        log.warn("[x]---> Session failed to create. XToken {}", event.xToken);
        //TODO add SessionManager call to update session status
    }

    @Async
    @TransactionalEventListener
    @Transactional(readOnly = true)
    public void activated(SessionActivated event) {
        log.info("[x]---> Session activated. XToken {}", event.xToken);
        SessionId sessionId = sessionRepository.findSessionIdByXToken(event.xToken);
        sessionManager.activate(sessionId.getId()).observable().subscribe(onSuccess, onError);
    }

    @Async
    @Transactional
    @TransactionalEventListener
    public void closeRequested(SessionCloseRequested event) {
        log.info("[x] Session CLOSE_REQUESTED, XToken: {}", event.getXToken());
        RemoteRequestEmulator.mayaRequest();
        sessionRepository.findByXToken(event.getXToken()).map(Session::markClosed).ifPresent(sessionRepository::save);
    }

    @Async
    @TransactionalEventListener
    @Transactional(readOnly = true)
    public void closed(SessionClosed event) {
        log.info("[x] Session closed. XToken: {}", event.getXToken());
        SessionId sessionId = sessionRepository.findSessionIdByXToken(event.xToken);
        sessionManager.confirmClose(sessionId.getId()).observable().subscribe(onSuccess, onError);
    }

}
