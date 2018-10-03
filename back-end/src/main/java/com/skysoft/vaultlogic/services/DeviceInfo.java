package com.skysoft.vaultlogic.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceInfo {

    @JsonProperty("short_id")
    public final String shortId;

    @JsonProperty("formatted_address")
    public final String formattedAddress;

    public final String info;

    public final String timezone;

}
