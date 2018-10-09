package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class CashDevicesStatus extends StatusCode {

    @JsonProperty("acceptor_enabled")
    private boolean acceptorEnabled;

    @JsonProperty("dispenser_enabled")
    private boolean dispenserEnabled;

    @JsonProperty("recycler_enabled")
    private boolean recyclerEnabled;

    @JsonProperty("actual_amount")
    private BigDecimal actualAmount;

    @JsonProperty("requested_amount")
    private BigDecimal requestedAmount;

}