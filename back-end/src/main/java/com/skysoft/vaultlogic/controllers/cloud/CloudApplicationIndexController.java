package com.skysoft.vaultlogic.controllers.cloud;

import com.skysoft.vaultlogic.services.SessionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigInteger;

import static java.lang.String.format;

@Slf4j
@RestController
@Profile("cloud")
@AllArgsConstructor
@RequestMapping("/index/{appId}")
public class CloudApplicationIndexController {

    private final SessionService sessionService;

    @GetMapping
    public RedirectView getApplicationIndexPage(@PathVariable BigInteger appId, @RequestParam("token") String xToken) {
        log.info("[x] Application index request. APP ID: {}, XToken: {}", appId, xToken);
        Pair<String, BigInteger> appUrlSessionId = sessionService.createSession(appId, xToken);
        String applicationUrl = appUrlSessionId.getFirst();
        BigInteger token = appUrlSessionId.getSecond();
        log.info("[x] Redirecting to: {}, token: {}", applicationUrl, token);
        return new RedirectView(format("%s?token=%d", applicationUrl, token));
    }

}
