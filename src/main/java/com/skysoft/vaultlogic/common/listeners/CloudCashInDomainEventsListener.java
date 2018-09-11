package com.skysoft.vaultlogic.common.listeners;

import com.skysoft.vaultlogic.blockchain.contracts.CashInOracle;
import com.skysoft.vaultlogic.common.domain.cashin.events.*;
import com.skysoft.vaultlogic.common.request.EnableCashAcceptorRequest;
import com.skysoft.vaultlogic.web.service.CashInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.RequestEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.net.URI;

import static java.lang.String.valueOf;
import static java.lang.System.currentTimeMillis;
import static java.net.URI.create;

@Slf4j
@Component
@Profile("cloud-quorum")
public class CloudCashInDomainEventsListener {

    private static final String ENABLE_CASH_ACCEPTOR_URL = String.format("%s%s", "https://api-staging.maya.tech/open", "/device/cash/enable");
    private static final String DISABLE_CASH_ACCEPTOR_URL = String.format("%s%s", "https://api-staging.maya.tech/open", "/device/cash/disable");
    private static final String X_NONCE_HEADER = "X-Nonce";
    private static final String X_TOKEN_HEADER = "X-Token";

    private final CashInService cashInService;
    private final CashInOracle cashInOracle;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    public CloudCashInDomainEventsListener(CashInService cashInService, CashInOracle cashInOracle, OAuth2RestTemplate oAuth2RestTemplate) {
        this.cashInService = cashInService;
        this.cashInOracle = cashInOracle;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    @Async
    @TransactionalEventListener
    public void created(CashInCreated event) {
        log.info("[x]---> CASH IN CREATED EVENT, ID: {}, XTOKEN: {}", event.getId(), event.getXToken());

        log.info("[x] ---> handling open cash in channel event. Data: {}", event);
        RequestEntity<EnableCashAcceptorRequest> requestEntity = RequestEntity.post(URI.create(ENABLE_CASH_ACCEPTOR_URL))
                .header(X_NONCE_HEADER, valueOf(currentTimeMillis()))
                .header(X_TOKEN_HEADER, event.getXToken())
                .body(EnableCashAcceptorRequest.defaultValues());
        try {
            log.info("[x] ---> ENABLE CASH ACCEPTOR maya request...");
            String response = oAuth2RestTemplate.exchange(requestEntity, String.class).getBody();
            log.info("[x] ---> Response: {}", response);
        } catch (Throwable e) {
            log.error("[x] ---> Failed to send request. Reason: {}", e.getMessage());
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

        log.info("[x] ---> handling close cash in channel event. Data: {}", event);
        RequestEntity<Void> requestEntity = RequestEntity.post(create(DISABLE_CASH_ACCEPTOR_URL))
                .header(X_NONCE_HEADER, valueOf(currentTimeMillis()))
                .header(X_TOKEN_HEADER, event.getXToken())
                .build();
        try {
            log.info("[x] ---> Sending DISABLE CASH ACCEPTOR maya request...");
            String response = oAuth2RestTemplate.exchange(requestEntity, String.class).getBody();
            log.info("[x] ---> Response: {}", response);
        } catch (Throwable e) {
            log.error("[x] ---> Failed to send request. Reason: {}", e.getMessage());
//            failed to close cash in channel
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
