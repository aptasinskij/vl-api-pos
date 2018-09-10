package com.skysoft.vaultlogic.web.controller.local;

import com.skysoft.vaultlogic.web.postback.api.Event;
import com.skysoft.vaultlogic.web.postback.api.EventResponse;
import com.skysoft.vaultlogic.web.postback.api.RootPostBackRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post-back")
public class LocalPostBackController {

    private final RootPostBackRouter postBackRouter;
    private static final String X_TOKEN = "X-Token";

    @Autowired
    public LocalPostBackController(RootPostBackRouter postBackRouter) {
        this.postBackRouter = postBackRouter;
    }

    @PostMapping
    public ResponseEntity<EventResponse> postBack(@RequestBody Event<?> event, @RequestHeader(X_TOKEN) String xToken) {
        return ResponseEntity.ok(postBackRouter.handle(event, xToken));
    }

}
