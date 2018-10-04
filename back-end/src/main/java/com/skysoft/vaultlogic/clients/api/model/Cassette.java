package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class Cassette {

    private String id;

    private BigDecimal count;

    private String denomination;

    private BigDecimal amount;

    @JsonProperty("max_count")
    private BigDecimal maxCount;

    private Type type;

    public enum Type {
        cash, coin
    }

}
