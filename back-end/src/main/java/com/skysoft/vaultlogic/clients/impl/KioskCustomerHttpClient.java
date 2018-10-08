package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskCustomer;
import com.skysoft.vaultlogic.clients.api.model.Customer;
import com.skysoft.vaultlogic.clients.api.model.CustomerId;
import com.skysoft.vaultlogic.clients.api.model.CustomerIdPhone;
import com.skysoft.vaultlogic.clients.api.model.CustomerPhone;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.clients.MayaHeaders.X_TOKEN_HEADER;
import static io.vavr.API.Try;

@Service
@AllArgsConstructor
public class KioskCustomerHttpClient implements KioskCustomer {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    @Override
    public Either<Throwable, CustomerId> setCustomer(String xToken, String phoneNumber) {
        return Try(() -> rest.exchange(buildSetCustomerInSessionRequestEntity(xToken, phoneNumber), CustomerId.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    @Override
    public Either<Throwable, Customer> getCustomer(String xToken, CustomerIdPhone customerIdPhone) {
        return Try(() -> rest.exchange(buildGetCustomerInfoRequestEntity(xToken, customerIdPhone), Customer.class))
                .map(HttpEntity::getBody)
                .toEither();
    }

    private RequestEntity<CustomerPhone> buildSetCustomerInSessionRequestEntity(String xToken, String phoneNumber) {
        return RequestEntity.post(URI.create(maya.getSetCustomerInSessionUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(CustomerPhone.of(phoneNumber));
    }

    private RequestEntity<CustomerIdPhone> buildGetCustomerInfoRequestEntity(String xToken, CustomerIdPhone customer) {
        return RequestEntity.post(URI.create(maya.getCustomerInformationUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(customer);
    }

}