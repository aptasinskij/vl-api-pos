package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerIdPhone {

    private String phone;

    @JsonProperty("customer_id")
    private String customerId;

}
