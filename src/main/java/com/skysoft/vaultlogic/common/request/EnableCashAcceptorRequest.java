package com.skysoft.vaultlogic.common.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigInteger;

@Getter
public class EnableCashAcceptorRequest {

    @JsonProperty("requested_amount")
    private BigInteger requestedAmount = BigInteger.valueOf(100);

    @JsonProperty("acceptable_denominations")
    private int[] acceptableDenominations = {1, 2, 5, 10, 20, 50, 100, 200};

    @JsonProperty("acceptance_control")
    private boolean acceptanceControl = false;

    public static EnableCashAcceptorRequest defaultValues() {
        return new EnableCashAcceptorRequest();
    }

}
