package com.skysoft.vaultlogic.postback.impl.protocol.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.postback.api.EventData;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CashAccept implements EventData {

    @JsonProperty("current_amount")
    private BigDecimal currentAmount;

    @JsonProperty("actual_amount")
    private BigDecimal actualAmount;

    @JsonProperty("requested_amount")
    private BigDecimal requestedAmount;

}
