package com.skysoft.vaultlogic.common.listeners;

import com.skysoft.vaultlogic.blockchain.contracts.CashInOracle;
import com.skysoft.vaultlogic.common.domain.cashin.events.CashInCloseRequested;
import com.skysoft.vaultlogic.common.domain.cashin.events.CashInClosed;
import com.skysoft.vaultlogic.common.domain.cashin.events.CashInCreated;
import com.skysoft.vaultlogic.common.domain.cashin.events.CashInOpened;
import com.skysoft.vaultlogic.web.service.CashInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class CashInDomainEventsListener {

    private final CashInService cashInService;
    private final CashInOracle cashInOracle;

    @Autowired
    public CashInDomainEventsListener(CashInService cashInService, CashInOracle cashInOracle) {
        this.cashInService = cashInService;
        this.cashInOracle = cashInOracle;
    }

    @Async
    @TransactionalEventListener
    public void created(CashInCreated event) {
        log.info("[x]---> CASH IN CREATED EVENT, ID: {}, XTOKEN: {}", event.getId(), event.getXToken());
        try {
            log.info("[x] Sending request to MAYA");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn("[x] Thread was interrupted", e);
            Thread.currentThread().interrupt();
        }
        cashInService.confirmOpened(event.getId());
    }

    @Async
    @TransactionalEventListener
    public void opened(CashInOpened event) {
        log.info("[x]---> CASH IN OPENED EVENT. ID: {}", event.getId());
        cashInOracle.confirmOpen(event.getId())
                .sendAsync()
                .thenAccept(tx -> log.info("[x] Confirmed, TX: {}", tx.getTransactionHash()))
                .exceptionally(th -> {
                    log.error("[x] Error during confirmation", th);
                    return null;
                });
    }

    @Async
    @TransactionalEventListener
    public void closeRequested(CashInCloseRequested event) {
        log.info("[x]---> CASH IN CLOSE REQUESTED. ID: {}, X-TOKEN: {} ", event.getChannelId(), event.getXToken());
        try {
            log.info("[x] Sending: CLOSE CASH ACCEPTOR");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn("[x] Thread was interrupted", e);
            Thread.currentThread().interrupt();
        }
        cashInService.confirmClosed(event.getChannelId());
    }

    @Async
    @TransactionalEventListener
    public void closed(CashInClosed event) {
        log.info("[x]---> CASH IN CLOSED EVENT. ID: {} ", event.getChannelId());
        cashInOracle.confirmClose(event.getChannelId()).sendAsync()
                .thenAccept(tx -> log.info("[x] Confirmed, TX: {}", tx.getTransactionHash()))
                .exceptionally(th -> {
                    log.error("[x] Error during confirmation", th);
                    return null;
                });
    }

}
