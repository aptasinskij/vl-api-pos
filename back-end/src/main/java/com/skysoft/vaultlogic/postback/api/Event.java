package com.skysoft.vaultlogic.postback.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class Event<T extends Serializable> implements Serializable {

    @JsonProperty("event_type")
    private String eventType;

    @JsonProperty("device_type")
    private String deviceType;

    @JsonProperty("action_type")
    private String actionType;

    private T data;

}
