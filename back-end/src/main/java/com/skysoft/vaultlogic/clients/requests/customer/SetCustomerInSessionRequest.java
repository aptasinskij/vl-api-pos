package com.skysoft.vaultlogic.clients.requests.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value(staticConstructor = "of")
public class SetCustomerInSessionRequest {

    @JsonProperty("phone")
    private final String phoneNumber;

}