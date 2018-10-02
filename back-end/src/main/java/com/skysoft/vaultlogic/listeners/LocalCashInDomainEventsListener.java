package com.skysoft.vaultlogic.listeners;

import com.skysoft.vaultlogic.contracts.CashInOracle;
import com.skysoft.vaultlogic.common.domain.cashin.CashInChannel;
import com.skysoft.vaultlogic.common.domain.cashin.CashInRepository;
import com.skysoft.vaultlogic.common.domain.cashin.events.*;
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

    private final CashInOracle cashInOracle;

    private final CashInRepository cashInRepository;

    @Autowired
    public LocalCashInDomainEventsListener(CashInOracle cashInOracle, CashInRepository repository) {
        this.cashInRepository = repository;
        this.cashInOracle = cashInOracle;
    }

    @Async
    @TransactionalEventListener
    public void creating(CashInCreating event) {
        log.info("[x]---> CASH IN CREATING EVENT, ID: {}", event.getChannelId());
        try {
            log.info("[x] Sending request to MAYA");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn("[x] Thread was interrupted", e);
            Thread.currentThread().interrupt();
        }
        cashInRepository.findById(event.getChannelId()).map(CashInChannel::markActive).ifPresent(cashInRepository::save);
    }

    @Async
    @TransactionalEventListener
    public void activated(CashInActivated event) {
        log.info("[x]---> CASH IN ACTIVATED EVENT. ID: {}", event.getChannelId());
        cashInOracle.confirmOpen(event.getChannelId())
                .sendAsync()
                .thenAccept(tx -> log.info("[x] Confirmed, TX: {}", tx.getTransactionHash()))
                .exceptionally(th -> {
                    log.error("[x] Error during confirmation", th);
                    return null;
                });
    }

    @Async
    @TransactionalEventListener
    public void failedToCreate(CashInFailedToCreate event) {
        log.info("[x]---> CASH IN FAILED TO CREATE. ID: {}");
        //TODO add call to CashInOracle#failedToCreate
    }

    @Async
    @TransactionalEventListener
    public void closeRequested(CashInCloseRequested event) {
        log.info("[x]---> CASH IN CLOSE REQUESTED. ID: {}", event.getChannelId());
        try {
            log.info("[x] Sending: CLOSE CASH ACCEPTOR");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn("[x] Thread was interrupted", e);
            Thread.currentThread().interrupt();
        }
        cashInRepository.findByChannelId(event.getChannelId()).map(CashInChannel::markClosed).ifPresent(cashInRepository::save);
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
    public void failedToClose(CashInFailedToCreate event) {
        log.info("[x]---> CASH IN FAILED TO CREATE. ID: {}");
        //TODO add call to CashInOracle#failedToCreate
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
