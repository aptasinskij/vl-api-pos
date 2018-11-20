package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class DispenseCash {

    @JsonProperty("requested_amount")
    private BigDecimal amount;

    private List<Cassette> cassettes;

    public DispenseCash(BigDecimal amount, List<Cassette> cassettes) {
        this.amount = amount;
        this.cassettes = cassettes;
    }

    public static DispenseCash from(BigInteger amount, List<Cassette> cassettes) {
        return new DispenseCash(new BigDecimal(amount), cassettes);
    }
}