package com.skysoft.vaultlogic.listeners;

import com.skysoft.vaultlogic.common.domain.session.Session;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.session.SessionTxLogRepository;
import com.skysoft.vaultlogic.common.domain.session.events.SessionActivated;
import com.skysoft.vaultlogic.common.domain.session.events.SessionCreating;
import com.skysoft.vaultlogic.common.domain.session.events.SessionFailedToCreate;
import com.skysoft.vaultlogic.contracts.SessionManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@Slf4j
@Component
@Profile("ganache")
@AllArgsConstructor
public class LocalSessionDomainEventsListener {

    private final SessionManager sessionManager;
    private final SessionRepository sessionRepository;
    private final SessionTxLogRepository sessionTxLogRepository;

    @Async
    @Transactional
    @TransactionalEventListener
    public void creating(SessionCreating event) {
        log.info("[x]---> Session creating with XToken: {}", event.xToken);
        Session session = sessionRepository.findByXTokenJoinApplicationAndKiosk(event.xToken).orElseThrow(RuntimeException::new);
        sessionManager.createSession(session.getId(), session.getApplication().getId(), session.getXToken(), session.getKiosk().getShortId())
                .observable()
                .subscribe(this::onNextSaved, this::onErrorSave);
    }

    private void onNextSaved(TransactionReceipt transactionReceipt) {
        log.info("[x] Saved session to SC: {}", transactionReceipt.getTransactionHash());
    }

    private Void onErrorSave(Throwable throwable) {
        log.error("[x] Failed to save", throwable);
        return null;
    }

    @Async
    @TransactionalEventListener
    public void failedToCreate(SessionFailedToCreate event) {
        log.warn("[x]---> Session failed to create. XToken {}", event.xToken);
    }

    @Async
    @TransactionalEventListener
    public void activated(SessionActivated event) {
        log.info("[x]---> Session activated. XToken {}", event.xToken);
        Session session = sessionRepository.findByXToken(event.xToken).orElseThrow(RuntimeException::new);
        sessionManager.activate(session.getId())
                .sendAsync()
                .thenAccept(tx -> log.info("[x] Activation saved to SC: HASH: {}", tx.getTransactionHash()))
                .exceptionally(throwable -> {
                    log.error("[x] Error saving activation to SC", throwable);
                    return null;
                });
    }

}
