package com.skysoft.vaultlogic.web.maya.clients.requests.cashDevice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.web.maya.clients.requestModels.cashDevice.EnableCashAcceptorBody;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Value(staticConstructor = "of")
public class EnableCashAcceptorRequest {

    @JsonProperty("requested_amount")
    private BigDecimal requestedAmount;

    @JsonProperty("acceptable_denominations")
    private List<Float> acceptableDenominations;

    @JsonProperty("acceptance_control")
    private Boolean acceptanceControl;

    public static EnableCashAcceptorRequest of(EnableCashAcceptorBody enableCashAcceptor) {
        return EnableCashAcceptorRequest.builder()
                .requestedAmount(enableCashAcceptor.getRequestedAmount())
                .acceptableDenominations(enableCashAcceptor.getAcceptableDenominations())
                .acceptanceControl(enableCashAcceptor.getAcceptanceControl())
                .build();
    }
}