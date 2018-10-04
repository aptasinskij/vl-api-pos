package com.skysoft.vaultlogic.web.maya.clients.requestModels.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoBody {

    private String phoneNumber;
    private String customerId;

}
