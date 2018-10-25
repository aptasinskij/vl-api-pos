package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigInteger;
import java.util.List;

import static java.util.Collections.singletonList;

@Getter
public class CashAcceptorConfig {

    @JsonProperty("requested_amount")
    private BigInteger requestedAmount;

    @JsonProperty("acceptable_denominations")
    private List<Float> acceptableDenominations;

    @JsonProperty("acceptance_control")
    private boolean acceptanceControl;

    private CashAcceptorConfig(BigInteger requestedAmount, List<Float> acceptableDenominations) {
        this.requestedAmount = requestedAmount;
        this.acceptableDenominations = acceptableDenominations;
        this.acceptanceControl = false;
    }

    public static CashAcceptorConfig onlyTwentiesOf(BigInteger requestedAmount) {
        return new CashAcceptorConfig(requestedAmount, singletonList(20.0F));
    }

}