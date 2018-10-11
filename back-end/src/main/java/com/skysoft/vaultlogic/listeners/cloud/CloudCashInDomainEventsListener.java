package com.skysoft.vaultlogic.listeners.cloud;

import com.skysoft.vaultlogic.common.domain.cashin.CashInChannel;
import com.skysoft.vaultlogic.common.domain.cashin.CashInRepository;
import com.skysoft.vaultlogic.common.domain.cashin.events.*;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.session.projections.SessionXToken;
import com.skysoft.vaultlogic.common.protocol.request.EnableCashAcceptorRequest;
import com.skysoft.vaultlogic.contracts.CashInOracle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.RequestEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.net.URI;

import static java.lang.String.valueOf;
import static java.lang.System.currentTimeMillis;
import static java.net.URI.create;

@Slf4j
@Component
@Profile("cloud")
public class CloudCashInDomainEventsListener {

    private static final String ENABLE_CASH_ACCEPTOR_URL = String.format("%s%s", "https://api-staging.maya.tech/open", "/device/cash/enable");
    private static final String DISABLE_CASH_ACCEPTOR_URL = String.format("%s%s", "https://api-staging.maya.tech/open", "/device/cash/disable");
    private static final String X_NONCE_HEADER = "X-Nonce";
    private static final String X_TOKEN_HEADER = "X-Token";

    private final CashInOracle cashInOracle;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    private final CashInRepository cashInRepository;
    private final SessionRepository sessionRepository;

    @Autowired
    public CloudCashInDomainEventsListener(CashInOracle cashInOracle,
                                           CashInRepository cashInRepository,
                                           SessionRepository sessionRepository,
                                           OAuth2RestTemplate oAuth2RestTemplate) {
        this.cashInOracle = cashInOracle;
        this.cashInRepository = cashInRepository;
        this.sessionRepository = sessionRepository;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    @Async
    @TransactionalEventListener
    public void created(CashInCreating event) {
        SessionXToken sessionXToken = sessionRepository.findSessionXTokenByCashInChannels_ChannelId(event.getChannelId());
        log.info("[x]---> CASH IN CREATED EVENT, ID: {}, XTOKEN: {}", event.getChannelId(), sessionXToken.getXToken());

        log.info("[x] ---> handling open cash in channel event. Data: {}", event);
        RequestEntity<EnableCashAcceptorRequest> requestEntity = RequestEntity.post(URI.create(ENABLE_CASH_ACCEPTOR_URL))
                .header(X_NONCE_HEADER, valueOf(currentTimeMillis()))
                .header(X_TOKEN_HEADER, sessionXToken.getXToken())
                .body(EnableCashAcceptorRequest.defaultValues());
        try {
            log.info("[x] ---> ENABLE CASH ACCEPTOR maya request...");
            String response = oAuth2RestTemplate.exchange(requestEntity, String.class).getBody();
            log.info("[x] ---> Response: {}", response);
        } catch (Throwable e) {
            log.error("[x] ---> Failed to send request. Reason: {}", e.getMessage());
        }
        cashInRepository.findByChannelId(event.getChannelId()).map(CashInChannel::markActive).ifPresent(cashInRepository::save);
    }

    @Async
    @TransactionalEventListener
    public void opened(CashInActivated event) {
        log.info("[x]---> CASH IN OPENED EVENT. ID: {}", event.getChannelId());
        cashInOracle.confirmOpen(event.getChannelId())
                .sendAsync()
                .thenAccept(tx -> log.info("[x] Confirmed, TX: {}", tx.getTransactionHash()))
                .exceptionally(th -> {
                    log.error("[x] Error during confirmation", th);
                    return null;
                });
    }

    @Async
    @Transactional
    @TransactionalEventListener
    public void closeRequested(CashInCloseRequested event) {
        SessionXToken sessionXToken = sessionRepository.findSessionXTokenByCashInChannels_ChannelId(event.getChannelId());
        log.info("[x]---> CASH IN CLOSE REQUESTED. ID: {}, X-TOKEN: {} ", event.getChannelId(), sessionXToken.getXToken());
        log.info("[x] ---> handling close cash in channel event. Data: {}", event);
        RequestEntity<Void> requestEntity = RequestEntity.post(create(DISABLE_CASH_ACCEPTOR_URL))
                .header(X_NONCE_HEADER, valueOf(currentTimeMillis()))
                .header(X_TOKEN_HEADER, sessionXToken.getXToken())
                .build();
        try {
            log.info("[x] ---> Sending DISABLE CASH ACCEPTOR maya request...");
            String response = oAuth2RestTemplate.exchange(requestEntity, String.class).getBody();
            log.info("[x] ---> Response: {}", response);
        } catch (Throwable e) {
            log.error("[x] ---> Failed to send request. Reason: {}", e.getMessage());
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
