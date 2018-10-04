package com.skysoft.vaultlogic.web.maya.clients.api;

import com.skysoft.vaultlogic.web.maya.clients.responseModels.customer.CustomerSetInSessionInfo;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.customer.CustomerInfo;
import com.skysoft.vaultlogic.web.maya.clients.requestModels.customer.CustomerInfoBody;

public interface CustomerClient {

    CustomerSetInSessionInfo setCustomerInSession(String xToken, String phoneNumber);

    CustomerInfo getCustomerInformation(String xToken, CustomerInfoBody customerInfo);

}