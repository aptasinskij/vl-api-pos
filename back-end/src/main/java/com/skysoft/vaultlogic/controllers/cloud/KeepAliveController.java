package com.skysoft.vaultlogic.controllers.cloud;

import com.skysoft.vaultlogic.clients.api.KioskApplicationClient;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@Slf4j
@RestController
@Profile("cloud")
@RequestMapping("/")
public class KeepAliveController {

    private static final String VAULT_LOGIC_TOKEN = "Vault-Logic-Token";

    private final SessionRepository sessionRepository;

    private final KioskApplicationClient kioskApplicationClient;

    @Autowired
    protected KeepAliveController(SessionRepository sessionRepository, KioskApplicationClient kioskApplicationClient) {

        this.sessionRepository = sessionRepository;
        this.kioskApplicationClient = kioskApplicationClient;
    }

    @PostMapping("keep-alive")
    public void keepAlive(@RequestHeader(VAULT_LOGIC_TOKEN) BigInteger sessionId, @RequestBody KeepAliveRequest request) {
        String xToken = sessionRepository.findSessionXTokenById(sessionId).getxToken();
        Either<Throwable, StatusCode> response = kioskApplicationClient.keepAlive(xToken, request.getKeepalive());
        response.peekLeft(throwable -> log.error("[x] error sending keep alive request"));
        response.peek(statusCode -> log.info("[x] success sending keep alive"));
    }

    @PostMapping("activity")
    public void activity(@RequestHeader(VAULT_LOGIC_TOKEN) BigInteger sessionId) {
        String xToken = sessionRepository.findSessionXTokenById(sessionId).getxToken();
        Either<Throwable, StatusCode> response = kioskApplicationClient.clientActivity(xToken);
        response.peekLeft(throwable -> log.error("[x] error sending keep alive request"));
        response.peek(statusCode -> log.info("[x] success sending keep alive"));
    }

}
