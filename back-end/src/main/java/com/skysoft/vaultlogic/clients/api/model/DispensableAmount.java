package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class DispensableAmount extends StatusCode {

    @JsonAlias({"available_amount", "requested_amount"})
    private BigDecimal amount;

}