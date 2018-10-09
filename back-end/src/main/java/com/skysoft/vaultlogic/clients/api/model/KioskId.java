package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KioskId {

    @JsonProperty("device_id")
    public final String deviceId;

    public KioskId(String deviceId) {
        this.deviceId = deviceId;
    }

    public static KioskId of(String deviceId) {
        return new KioskId(deviceId);
    }

}