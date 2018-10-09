package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KeepAlive {

    @JsonProperty("keepalive")
    public final String token;

    public KeepAlive(String token) {
        this.token = token;
    }

    public static KeepAlive of(String token) {
        return new KeepAlive(token);
    }

}