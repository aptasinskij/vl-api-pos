package com.skysoft.vaultlogic.postback.impl.protocol.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.postback.api.EventData;
import lombok.Data;

@Data
public class QRScanned implements EventData {

    @JsonProperty("qr")
    public final String data;

}
