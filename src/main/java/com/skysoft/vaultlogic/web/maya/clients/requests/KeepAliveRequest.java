package com.skysoft.vaultlogic.web.maya.clients.requests;

import lombok.Value;

@Value(staticConstructor = "of")
public class KeepAliveRequest {

    private final String keepalive;

}
