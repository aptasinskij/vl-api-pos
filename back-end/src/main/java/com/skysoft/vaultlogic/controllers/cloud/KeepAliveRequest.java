package com.skysoft.vaultlogic.controllers.cloud;

import lombok.Value;

@Value(staticConstructor = "of")
public class KeepAliveRequest {

    private final String keepalive;

}
