package com.skysoft.vaultlogic.controllers.local;

import com.skysoft.vaultlogic.common.domain.session.Session;
import com.skysoft.vaultlogic.services.ApplicationService;
import com.skysoft.vaultlogic.services.SessionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigInteger;
import java.net.URI;

import static java.lang.String.format;

@Slf4j
@RestController
@Profile("ganache")
@AllArgsConstructor
@RequestMapping("/index/{appId}")
public class LocalApplicationIndexController {

    private final ApplicationService applicationService;
    private final SessionService sessionService;

    @GetMapping
    public RedirectView getApplicationIndexPage(@PathVariable BigInteger appId, @RequestParam("token") String xToken) {
        URI appIndexUri = applicationService.getApplicationUri(appId);
        Session session = sessionService.createApplicationSession(appId, xToken);
        sessionService.activate(session.getId());
        return new RedirectView(format("%s?token=%d", appIndexUri.toString(), session.getId()));
    }

}
