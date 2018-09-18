package com.skysoft.vaultlogic.web.controller.local;

import com.skysoft.vaultlogic.common.domain.session.Session;
import com.skysoft.vaultlogic.web.service.ApplicationService;
import com.skysoft.vaultlogic.web.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.net.URI;
import java.util.Random;

import static org.springframework.http.MediaType.TEXT_HTML;

@Slf4j
@RestController
@Profile("ganache")
@RequestMapping("/index/{appId}")
public class LocalApplicationIndexController {

    private static final String VL_TOKEN = "Vault-Logic-Token";

    private final ApplicationService applicationService;
    private final SessionService sessionService;
    private final RestTemplate restTemplate;

    @Autowired
    public LocalApplicationIndexController(ApplicationService applicationService,
                                           SessionService sessionService,
                                           RestTemplate restTemplate) {
        this.applicationService = applicationService;
        this.sessionService = sessionService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<String> getApplicationIndexPage(@PathVariable BigInteger appId, @RequestParam("token") String xToken) {
        URI appIndexUri = applicationService.getApplicationUri(appId);
        Session session = sessionService.createApplicationSession(appId, xToken);
        try {
            RequestEntity<Void> request = buildRequest(appIndexUri, String.valueOf(session.getId()));
            emulateRequestToMaya();
            sessionService.activate(session);
            return restTemplate.exchange(request, String.class);
        } catch (HttpClientErrorException e) {
            sessionService.failedToCreate(session);
        }
        return ResponseEntity.notFound().build();
    }

    private void emulateRequestToMaya() {
        try {
            Thread.sleep(1500);
            if (new Random().nextBoolean()) return;
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("[x] Thread was interrupted");
        }
    }

    private RequestEntity<Void> buildRequest(URI appIndexUri, String token) {
        return RequestEntity.get(appIndexUri)
                .accept(TEXT_HTML)
                .header(VL_TOKEN, token)
                .build();
    }

}
