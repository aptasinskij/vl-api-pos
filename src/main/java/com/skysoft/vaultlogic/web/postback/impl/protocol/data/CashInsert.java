package com.skysoft.vaultlogic.web.postback.impl.protocol.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.web.postback.api.EventData;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CashInsert implements EventData {

    @JsonProperty("current_amount")
    private BigDecimal currentAmount;

    @JsonProperty("actual_amount")
    private BigDecimal actualAmount;

    @JsonProperty("requested_amount")
    private BigDecimal requestedAmount;

}
