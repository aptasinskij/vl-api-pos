package com.skysoft.vaultlogic.common.domain.session.listeners;

import com.skysoft.vaultlogic.blockchain.contracts.SessionServiceApi;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.session.events.SessionActivated;
import com.skysoft.vaultlogic.common.domain.session.projections.SmartContractSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.math.BigInteger;

@Slf4j
@Component
public class SessionDomainEventsListener {

    private final SessionServiceApi sessionServiceApi;
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionDomainEventsListener(SessionServiceApi sessionServiceApi, SessionRepository sessionRepository) {
        this.sessionServiceApi = sessionServiceApi;
        this.sessionRepository = sessionRepository;
    }

    @Async
    @TransactionalEventListener
    public void handleSessionActivated(SessionActivated event) {
        log.info("[x]---> Session activated with XToken: {}", event.xToken);
        SmartContractSession session = sessionRepository.findSmartContractSessionByXToken(event.xToken);
        sessionServiceApi.createSession(BigInteger.valueOf(session.getId()), BigInteger.valueOf(session.getApplicationId()), session.getXToken())
                .sendAsync()
                .thenAccept(tx -> log.info("[x] Saved to Smart Contract. TX: {}", tx.getTransactionHash()))
                .exceptionally(throwable -> {
                    log.error("[x] Failed to save", throwable);
                    return null;
                });
    }

}
