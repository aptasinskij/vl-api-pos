package com.skysoft.vaultlogic.clients.requests.cashDevice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.clients.requestModels.cashDevice.Cassettes;
import com.skysoft.vaultlogic.clients.requestModels.cashDevice.GetDispensableAmountBody;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Builder
@Value(staticConstructor = "of")
public class GetDispensableAmountRequest {

    @JsonProperty("requested_amount")
    private BigDecimal requestedAmount;

    private Cassettes cassettes;

    public static GetDispensableAmountRequest of(GetDispensableAmountBody getDispensableAmount) {
        return GetDispensableAmountRequest.builder()
                .requestedAmount(getDispensableAmount.getRequestedAmount())
                .cassettes(getDispensableAmount.getCassettes())
                .build();
    }

}