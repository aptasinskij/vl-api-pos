package com.skysoft.vaultlogic.listeners.cloud;

import com.skysoft.vaultlogic.clients.api.KioskCashDevices;
import com.skysoft.vaultlogic.clients.api.model.CashAcceptorConfig;
import com.skysoft.vaultlogic.common.domain.cashin.CashInChannel;
import com.skysoft.vaultlogic.common.domain.cashin.CashInRepository;
import com.skysoft.vaultlogic.common.domain.cashin.events.*;
import com.skysoft.vaultlogic.common.domain.cashin.projections.CashInMaxBalance;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.session.projections.SessionXToken;
import com.skysoft.vaultlogic.contracts.CashInOracle;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import rx.functions.Action1;

import static com.skysoft.vaultlogic.clients.api.model.CashAcceptorConfig.onlyTwentiesOf;

@Slf4j
@Component
@Profile("cloud")
@AllArgsConstructor
public class CloudCashInDomainEventsListener {

    private static final Action1<TransactionReceipt> CONFIRMED = tx -> log.info("[x] Confirmed, TX: {}", tx.getTransactionHash());
    private static final Action1<Throwable> FAILED = throwable -> log.error("[x] Error during confirmation", throwable);

    private final CashInOracle cashInOracle;
    private final CashInRepository cashInRepository;
    private final SessionRepository sessionRepository;
    private final KioskCashDevices kioskCashDevices;

    @Async
    @Transactional
    @TransactionalEventListener
    public void created(CashInCreating event) {
        log.info("[x]---> CASH IN CREATED EVENT, ID: {}", event.getChannelId());
        CashInMaxBalance maxBalance = cashInRepository.findCashInMaxBalanceByChannelId(event.getChannelId());
        SessionXToken sessionXToken = sessionRepository.findSessionXTokenByCashInChannels_ChannelId(event.getChannelId());
        CashAcceptorConfig cashAcceptorConfig = onlyTwentiesOf(maxBalance.getMaxBalance());
        kioskCashDevices.enableCashAcceptor(sessionXToken.getxToken(), cashAcceptorConfig)
                .onSuccess(cashAcceptorStatus -> log.info("[x] successfully enabled cash acceptor"))
                .onFailure(throwable -> log.error("[x] failed to enable cash acceptor")).toJavaOptional()
                .flatMap(cashAcceptorStatus -> cashInRepository.findByChannelId(event.getChannelId()))
                .map(CashInChannel::markActive)
                .ifPresent(cashInRepository::save);
    }

    @Async
    @TransactionalEventListener
    public void opened(CashInActivated event) {
        log.info("[x]---> CASH IN OPENED EVENT. ID: {}", event.getChannelId());
        cashInOracle.successOpen(event.getChannelId()).observable().take(1).subscribe(CONFIRMED, FAILED);
    }

    @Async
    @Transactional
    @TransactionalEventListener
    public void closeRequested(CashInCloseRequested event) {
        SessionXToken sessionXToken = sessionRepository.findSessionXTokenByCashInChannels_ChannelId(event.getChannelId());
        log.info("[x]---> CASH IN CLOSE REQUESTED. ID: {}, X-TOKEN: {} ", event.getChannelId(), sessionXToken.getxToken());
        kioskCashDevices.disableCashAcceptor(sessionXToken.getxToken())
                .onSuccess(cashAcceptorStatus -> log.info("[x] successfully close cash acceptor"))
                .onFailure(throwable -> log.error("[x] failed to disable cash acceptor")).toJavaOptional()
                .flatMap(cashAcceptorStatus -> cashInRepository.findByChannelId(event.getChannelId()))
                .map(CashInChannel::markClosed)
                .ifPresent(cashInRepository::save);
    }

    @Async
    @TransactionalEventListener
    public void closed(CashInClosed event) {
        log.info("[x]---> CASH IN CLOSED EVENT. ID: {} ", event.getChannelId());
        cashInOracle.successClose(event.getChannelId()).observable().take(1).subscribe(CONFIRMED, FAILED);
    }

    @Async
    @TransactionalEventListener
    public void balanceUpdate(CashInBalanceUpdate event) {
        log.info("[x]---> UPDATE CASH IN BALANCE EVENT. ID: {} ", event.getChannelId());
        cashInOracle.increaseBalance(event.getChannelId(), event.getAmount()).observable().take(1).subscribe(CONFIRMED, FAILED);
    }

}
