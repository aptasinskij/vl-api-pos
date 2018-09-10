package com.skysoft.vaultlogic.web.controller.cloud;

import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.web.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static java.lang.String.valueOf;
import static java.lang.System.currentTimeMillis;

@RestController
@Profile("cloud-quorum")
@RequestMapping("/keep-alive")
public class KeepAliveController {

    private static final String KEEP_ALIVE_APPLICATION_URL = String.format("%s%s", "https://api-staging.maya.tech/open", "/application/keepalive");
    private static final String X_NONCE_HEADER = "X-Nonce";
    private static final String X_TOKEN_HEADER = "X-Token";
    private static final String KEEP_ALIVE_TOKEN = "Keep-Alive-Token";
    private static final String VAULT_LOGIC_TOKEN = "Vault-Logic-Token";

    private final OAuth2RestTemplate oAuth2RestTemplate;

    private final SessionService sessionService;
    private final SessionRepository sessionRepository;


    @Autowired
    protected KeepAliveController(OAuth2RestTemplate oAuth2RestTemplate, SessionService sessionService, SessionRepository sessionRepository) {
        this.oAuth2RestTemplate = oAuth2RestTemplate;
        this.sessionService = sessionService;
        this.sessionRepository = sessionRepository;
    }

    @PostMapping
    private void keepAlive(@RequestHeader(KEEP_ALIVE_TOKEN) String keepAliveToken, @RequestHeader(VAULT_LOGIC_TOKEN) String sessionId) {
        String xToken = sessionRepository.findById(Long.valueOf(sessionId))
                .orElseThrow(RuntimeException::new)
                .getXToken();
        oAuth2RestTemplate.exchange(buildRequestEntity(xToken, keepAliveToken), String.class);
    }

    private RequestEntity<KeepAliveRequest> buildRequestEntity(String xToken, String keepAliveToken) {
        return RequestEntity.post(URI.create(KEEP_ALIVE_APPLICATION_URL))
                .header(X_NONCE_HEADER, valueOf(currentTimeMillis()))
                .header(X_TOKEN_HEADER, xToken)
                .body(KeepAliveRequest.of(keepAliveToken));
    }

}
