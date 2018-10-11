package com.skysoft.vaultlogic.listeners.local;

import com.skysoft.vaultlogic.common.domain.cashin.CashInChannel;
import com.skysoft.vaultlogic.common.domain.cashin.CashInRepository;
import com.skysoft.vaultlogic.common.domain.cashin.events.*;
import com.skysoft.vaultlogic.contracts.CashInOracle;
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
@Profile("local")
@AllArgsConstructor
public class LocalCashInDomainEventsListener {

    private final CashInOracle cashInOracle;
    private final CashInRepository cashInRepository;

    private final Action1<TransactionReceipt> onSuccess = tx -> log.info("[x] Confirmed, TX: {}", tx.getTransactionHash());
    private final Action1<Throwable> onError = throwable -> log.error("[x] Error during confirmation", throwable);

    @Async
    @Transactional
    @TransactionalEventListener
    public void creating(CashInCreating event) {
        log.info("[x]---> CASH IN CREATING EVENT, ID: {}", event.getChannelId());
        RemoteRequestEmulator.mayaRequest();
        cashInRepository.findById(event.getChannelId()).ifPresent(channel -> cashInRepository.save(channel.markActive()));
    }

    @Async
    @TransactionalEventListener
    public void activated(CashInActivated event) {
        log.info("[x]---> CASH IN ACTIVATED EVENT. ID: {}", event.getChannelId());
        cashInOracle.confirmOpen(event.getChannelId()).observable().subscribe(onSuccess, onError);
    }

    @Async
    @TransactionalEventListener
    public void failedToCreate(CashInFailedToCreate event) {
        log.info("[x]---> CASH IN FAILED TO CREATE. ID: {}");
        //TODO add call to CashInOracle#failedToCreate
    }

    @Async
    @Transactional
    @TransactionalEventListener
    public void closeRequested(CashInCloseRequested event) {
        log.info("[x]---> CASH IN CLOSE REQUESTED. ID: {}", event.getChannelId());
        RemoteRequestEmulator.mayaRequest();
        cashInRepository.findByChannelId(event.getChannelId()).map(CashInChannel::markClosed).ifPresent(cashInRepository::save);
    }

    @Async
    @TransactionalEventListener
    public void closed(CashInClosed event) {
        log.info("[x]---> CASH IN CLOSED EVENT. ID: {} ", event.getChannelId());
        cashInOracle.confirmClose(event.getChannelId()).observable().subscribe(onSuccess, onError);
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
        cashInOracle.increaseBalance(event.getChannelId(), event.getAmount()).observable().subscribe(onSuccess, onError);
    }

}
