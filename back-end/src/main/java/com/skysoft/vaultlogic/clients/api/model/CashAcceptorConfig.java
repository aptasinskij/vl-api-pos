package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CashAcceptorConfig {

    @JsonProperty("requested_amount")
    private BigDecimal requestedAmount;

    @JsonProperty("acceptable_denominations")
    private List<Float> acceptableDenominations;

    @JsonProperty("acceptance_control")
    private boolean acceptanceControl;

    @Value(staticConstructor = "of")
    public static class KeepAlive {

        @JsonProperty("keepalive")
        private final String token;

    }
}