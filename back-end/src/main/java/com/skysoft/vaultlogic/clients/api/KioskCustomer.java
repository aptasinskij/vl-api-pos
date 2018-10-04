package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.CustomerId;
import com.skysoft.vaultlogic.clients.api.model.Customer;
import com.skysoft.vaultlogic.clients.api.model.CustomerIdPhone;

public interface KioskCustomer {

    CustomerId setCustomer(String xToken, String phoneNumber);

    Customer getCustomer(String xToken, CustomerIdPhone customerIdPhone);

}