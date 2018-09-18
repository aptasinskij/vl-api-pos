package com.skysoft.vaultlogic.common.listeners;

import com.skysoft.vaultlogic.blockchain.contracts.CashInOracle;
import com.skysoft.vaultlogic.common.domain.cashin.events.*;
import com.skysoft.vaultlogic.web.service.CashInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@Profile("ganache")
public class LocalCashInDomainEventsListener {

    private final CashInService cashInService;
    private final CashInOracle cashInOracle;

    @Autowired
    public LocalCashInDomainEventsListener(CashInService cashInService, CashInOracle cashInOracle) {
        this.cashInService = cashInService;
        this.cashInOracle = cashInOracle;
    }

    @Async
    @TransactionalEventListener
    public void created(CashInCreating event) {
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
    public void opened(CashInActivated event) {
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
        log.info("[x]---> CASH IN CLOSE REQUESTED. ID: {}, X-TOKEN: {} ", event.getChannelId(), event.getSessionId());
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

    @Async
    @TransactionalEventListener
    public void balanceUpdate(CashInBalanceUpdate event) {
        log.info("[x]---> UPDATE CASH IN BALANCE EVENT. ID: {} ", event.getChannelId());
        cashInOracle.increaseBalance(event.getChannelId(), event.getAmount()).sendAsync()
                .thenAccept(tx -> log.info("[x] Confirmed, TX: {}", tx.getTransactionHash()))
                .exceptionally(th -> {
                    log.error("[x] Error during confirmation", th);
                    return null;
                });
    }

}
