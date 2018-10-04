package com.skysoft.vaultlogic.web.maya.clients.requests.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.web.maya.clients.requestModels.customer.CustomerInfoBody;
import lombok.Value;

@Value(staticConstructor = "of")
public class CustomerInfoRequest {

    @JsonProperty("phone")
    private final String phoneNumber;

    @JsonProperty("customer_id")
    private final String customerId;

    public static CustomerInfoRequest of(CustomerInfoBody customerInfo) {
        return new CustomerInfoRequest(customerInfo.getPhoneNumber(), customerInfo.getCustomerId());
    }

}