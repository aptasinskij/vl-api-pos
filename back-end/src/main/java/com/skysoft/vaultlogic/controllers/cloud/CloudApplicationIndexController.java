package com.skysoft.vaultlogic.controllers.cloud;

import com.skysoft.vaultlogic.common.domain.session.Session;
import com.skysoft.vaultlogic.services.ApplicationService;
import com.skysoft.vaultlogic.services.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.net.URI;

import static java.lang.String.valueOf;
import static java.lang.System.currentTimeMillis;

@Slf4j
@RestController
@Profile("cloud-quorum")
@RequestMapping("/index/{appId}")
public class CloudApplicationIndexController {

    private static final String VL_TOKEN = "Vault-Logic-Token";

    private static final String LAUNCH_APPLICATION_URL = "https://api-staging.maya.tech/open/application/launch";
    private static final String X_TOKEN = "X-Token";
    private static final String X_NONCE = "x-Nonce";

    private final SessionService sessionService;
    private final ApplicationService applicationService;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    public CloudApplicationIndexController(SessionService sessionService, ApplicationService applicationService, OAuth2RestTemplate oAuth2RestTemplate) {
        this.sessionService = sessionService;
        this.applicationService = applicationService;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    @GetMapping
    public ResponseEntity<String> getApplicationIndexPage(@PathVariable BigInteger appId, @RequestParam("token") String xToken) {
        URI appIndexUri = applicationService.getApplicationUri(appId);
        Session session = sessionService.createApplicationSession(appId, xToken);
        ResponseEntity<String> responseEntity = getIndexPage(appIndexUri, session.getId().longValue());
        launchApplication(xToken);
        return responseEntity;
    }

    private ResponseEntity<String> getIndexPage(URI appIndexUri, Long sessionId) {
        try {
            return oAuth2RestTemplate.exchange(getApplicationIndexRequestEntity(appIndexUri, sessionId), String.class);
        } catch (Exception e) {
            log.error("[x] ---> Failed to get application index page. Reason: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private ResponseEntity<String> launchApplication(String xToken) {
        try {
            return oAuth2RestTemplate.exchange(getLaunchApplicationRequestEntity(xToken), String.class);
        } catch (Exception e) {
            log.error("[x] ---> Failed to launch application. Reason: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private RequestEntity<Void> getApplicationIndexRequestEntity(URI appIndexUri, Long sessionId) {
        return RequestEntity.get(appIndexUri)
                .accept(MediaType.TEXT_HTML)
                .header(VL_TOKEN, valueOf(sessionId))
                .build();
    }

    private RequestEntity<Void> getLaunchApplicationRequestEntity(String xToken) {
        return RequestEntity.post(URI.create(LAUNCH_APPLICATION_URL))
                .header(X_TOKEN, xToken)
                .header(X_NONCE, valueOf(currentTimeMillis()))
                .build();
    }

}
