package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class RecyclerStatus extends StatusCode {

    private boolean enabled;

    private List<Cassette> cassettes = new ArrayList<>();

    @JsonProperty("current_amount")
    private BigDecimal currentAmount;

    @JsonProperty("current_count")
    private BigDecimal currentCount;

}