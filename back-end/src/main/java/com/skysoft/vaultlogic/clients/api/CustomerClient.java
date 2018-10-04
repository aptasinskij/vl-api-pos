package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.responseModels.customer.CustomerSetInSessionInfo;
import com.skysoft.vaultlogic.clients.responseModels.customer.CustomerInfo;
import com.skysoft.vaultlogic.clients.requestModels.customer.CustomerInfoBody;

public interface CustomerClient {

    CustomerSetInSessionInfo setCustomerInSession(String xToken, String phoneNumber);

    CustomerInfo getCustomerInformation(String xToken, CustomerInfoBody customerInfo);

}