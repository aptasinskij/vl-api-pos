package com.skysoft.vaultlogic.listeners.cloud;

import com.skysoft.vaultlogic.common.domain.session.Session;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.session.events.SessionActivated;
import com.skysoft.vaultlogic.common.domain.session.events.SessionCloseRequested;
import com.skysoft.vaultlogic.common.domain.session.events.SessionClosed;
import com.skysoft.vaultlogic.common.domain.session.events.SessionCreating;
import com.skysoft.vaultlogic.contracts.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.client.RestClientException;

import java.net.URI;

@Slf4j
@Component
@Profile("cloud")
public class CloudSessionDomainEventsListener {

    private static final String CLOSE_APPLICATION_URL = String.format("%s%s", "https://api-staging.maya.tech/open", "/application/close");
    private static final String X_NONCE_HEADER = "X-Nonce";
    private static final String X_TOKEN_HEADER = "X-Token";

    private final SessionManager sessionManager;
    private final SessionRepository sessionRepository;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    public CloudSessionDomainEventsListener(SessionManager sessionManager, SessionRepository sessionRepository, OAuth2RestTemplate oAuth2RestTemplate) {
        this.sessionManager = sessionManager;
        this.sessionRepository = sessionRepository;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    @Async
    @TransactionalEventListener
    @Transactional(readOnly = true)
    public void creating(SessionCreating event) {
        log.info("[x]---> Session creating with XToken: {}", event.xToken);
        Session session = sessionRepository.findByXTokenJoinApplicationAndKiosk(event.xToken).orElseThrow(RuntimeException::new);
        sessionManager.createSession(session.getId(), session.getApplication().getId(), session.getXToken(), session.getKiosk().getShortId())
                .observable()
                .subscribe(
                        tx -> log.info("[x] session created in smart contract: {}", tx.getTransactionHash()),
                        error -> log.error("[x] error create session in smart contract: {}", error.getMessage())
                );
    }

    @Async
    @TransactionalEventListener
    @Transactional(readOnly = true)
    public void handleSessionActivated(SessionActivated event) {
        log.info("[x]---> Session activated with XToken: {}", event.xToken);
        Session session = sessionRepository.findByXTokenJoinApplicationAndKiosk(event.getXToken()).orElseThrow(RuntimeException::new);
        sessionManager.createSession(session.getId(), session.getApplication().getId(), session.getXToken(), session.getKiosk().getShortId())
                .sendAsync()
                .thenAccept(tx -> log.info("[x] Saved to Smart Contract. TX: {}", tx.getTransactionHash()))
                .exceptionally(throwable -> {
                    log.error("[x] Failed to save", throwable);
                    return null;
                });
    }

    @Async
    @TransactionalEventListener
    public void closeRequested(SessionCloseRequested event) {
        log.info("[x]---> Sending CLOSE APPLICATION MAYA REQUEST");
        RequestEntity<Void> request = getRequestEntity(event);
        try {
            if (responseIsOk(request)) {
                log.info("[x]---> Success in sending request");
                sessionRepository.findByXToken(event.getXToken())
                        .map(Session::markClosed)
                        .ifPresent(sessionRepository::save);
            }
        } catch (RestClientException e) {
            log.error("[x]---> Failed in sending request", e);
        }
    }

    private RequestEntity<Void> getRequestEntity(SessionCloseRequested event) {
        return RequestEntity.post(URI.create(CLOSE_APPLICATION_URL))
                .header(X_NONCE_HEADER, String.valueOf(System.currentTimeMillis()))
                .header(X_TOKEN_HEADER, event.getXToken())
                .build();
    }

    private boolean responseIsOk(RequestEntity<Void> request) {
        return oAuth2RestTemplate.exchange(request, String.class).getStatusCode().equals(HttpStatus.OK);
    }

    @Async
    @TransactionalEventListener
    public void closed(SessionClosed event) {
        log.info("[x]---> Session CLOSED EVENT HANDLING");
    }

}
