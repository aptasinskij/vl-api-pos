package com.skysoft.vaultlogic.common.listeners;

import com.skysoft.vaultlogic.blockchain.contracts.ISessionManager;
import com.skysoft.vaultlogic.common.domain.session.Session;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.session.SessionTxLog;
import com.skysoft.vaultlogic.common.domain.session.SessionTxLogRepository;
import com.skysoft.vaultlogic.common.domain.session.events.SessionCreating;
import com.skysoft.vaultlogic.common.domain.session.events.SessionFailedToCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.function.Function;

@Slf4j
@Component
@Profile("ganache")
public class LocalSessionDomainEventsListener {

    private final ISessionManager iSessionManager;
    private final SessionRepository sessionRepository;
    private final SessionTxLogRepository sessionTxLogRepository;

    @Autowired
    public LocalSessionDomainEventsListener(ISessionManager iSessionManager, SessionRepository sessionRepository, SessionTxLogRepository sessionTxLogRepository) {
        this.iSessionManager = iSessionManager;
        this.sessionRepository = sessionRepository;
        this.sessionTxLogRepository = sessionTxLogRepository;
    }

    @Async
    @Transactional
    @TransactionalEventListener
    public void creating(SessionCreating event) {
        log.info("[x]---> Session creating with XToken: {}", event.xToken);
        Session session = sessionRepository.findByXTokenJoinApplication(event.xToken).orElseThrow(RuntimeException::new);
        iSessionManager.createSession(session.getId(), session.getApplication().getId(), session.getXToken()).sendAsync()
                .thenApply(sessionCreatingLog(session))
                .thenAccept(sessionTxLogRepository::save)
                .exceptionally(this::handleConfirmationError);
    }

    @Async
    @TransactionalEventListener
    public void failedToCreate(SessionFailedToCreate ecent) {
        log.warn("[x]---> Session failed to create. XToken {}", ecent.xToken);
    }

    private Function<TransactionReceipt, SessionTxLog> sessionCreatingLog(Session session) {
        return tx -> new SessionTxLog(tx.getTransactionHash(), session, Session.Status.CREATING);
    }

    private Void handleConfirmationError(Throwable throwable) {
        log.error("[x] Failed to save", throwable);
        return null;
    }

}
