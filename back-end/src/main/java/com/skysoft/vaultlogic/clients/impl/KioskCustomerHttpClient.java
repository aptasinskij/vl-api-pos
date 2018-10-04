package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskCustomer;
import com.skysoft.vaultlogic.clients.api.model.Customer;
import com.skysoft.vaultlogic.clients.api.model.CustomerId;
import com.skysoft.vaultlogic.clients.api.model.CustomerIdPhone;
import com.skysoft.vaultlogic.clients.api.model.CustomerPhone;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.clients.MayaHeaders.X_TOKEN_HEADER;

@Service
@AllArgsConstructor
public class KioskCustomerHttpClient implements KioskCustomer {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    @Override
    public CustomerId setCustomer(String xToken, String phoneNumber) {
        try {
            ResponseEntity<CustomerId> exchange = oAuth2RestTemplate.exchange(buildSetCustomerInSessionRequestEntity(xToken, phoneNumber), CustomerId.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Customer getCustomer(String xToken, CustomerIdPhone customerIdPhone) {
        try {
            ResponseEntity<Customer> exchange = oAuth2RestTemplate.exchange(buildGetCustomerInfoRequestEntity(xToken, customerIdPhone), Customer.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    private RequestEntity<CustomerPhone> buildSetCustomerInSessionRequestEntity(String xToken, String phoneNumber) {
        return RequestEntity.post(URI.create(mayaProperties.getSetCustomerInSessionUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(CustomerPhone.of(phoneNumber));
    }

    private RequestEntity<CustomerIdPhone> buildGetCustomerInfoRequestEntity(String xToken, CustomerIdPhone customer) {
        return RequestEntity.post(URI.create(mayaProperties.getCustomerInformationUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(customer);
    }

}