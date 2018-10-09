package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScannerStatus extends StatusCode {

    @JsonProperty("scanner_enabled")
    private boolean enabled;
    private String qr;

}