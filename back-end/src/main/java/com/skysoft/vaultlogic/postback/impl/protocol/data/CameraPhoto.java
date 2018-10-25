package com.skysoft.vaultlogic.postback.impl.protocol.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.clients.api.model.Light;
import com.skysoft.vaultlogic.postback.api.EventData;
import lombok.Data;

@Data
public class CameraPhoto implements EventData {

    private final String id;

    private final Light light;

    private final Type type;

    @JsonProperty("file")
    private final String fileName;

    private final String url;

    public enum Type {
        photo, scan
    }

}