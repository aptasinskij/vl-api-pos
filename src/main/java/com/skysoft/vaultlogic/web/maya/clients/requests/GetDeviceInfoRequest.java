package com.skysoft.vaultlogic.web.maya.clients.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value(staticConstructor = "of")
public class GetDeviceInfoRequest {

    @JsonProperty("device_id")
    private String deviceId;

}