package com.skysoft.vaultlogic.web.maya.clients.responce.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetCustomerInSessionResponse extends BaseResponse {

    @JsonProperty("customer_id")
    private String customerId;

}