package com.skysoft.vaultlogic.web.controller.cloud;

import lombok.Value;

@Value(staticConstructor = "of")
public class KeepAliveRequest {

    private final String keepalive;

}
