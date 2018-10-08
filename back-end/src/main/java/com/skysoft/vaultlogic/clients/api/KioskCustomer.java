package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.CustomerId;
import com.skysoft.vaultlogic.clients.api.model.Customer;
import com.skysoft.vaultlogic.clients.api.model.CustomerIdPhone;
import io.vavr.control.Either;

public interface KioskCustomer {

    Either<Throwable, CustomerId> setCustomer(String xToken, String phoneNumber);

    Either<Throwable, Customer> getCustomer(String xToken, CustomerIdPhone customerIdPhone);

}