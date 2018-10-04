package com.skysoft.vaultlogic.clients.requests.cashDevice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.clients.requestModels.cashDevice.Cassettes;
import com.skysoft.vaultlogic.clients.requestModels.cashDevice.DispenseCashBody;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Builder
@Value(staticConstructor = "of")
public class DispenseCashRequest {

    @JsonProperty("requested_amount")
    private BigDecimal requestedAmount;

    private Cassettes cassettes;

    public static DispenseCashRequest of(DispenseCashBody dispenseCash) {
        return DispenseCashRequest.builder()
                .requestedAmount(dispenseCash.getRequestedAmount())
                .cassettes(dispenseCash.getCassettes())
                .build();
    }
}
