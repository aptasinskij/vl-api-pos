package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DispenseCash {

    @JsonProperty("requested_amount")
    private BigDecimal amount;

}