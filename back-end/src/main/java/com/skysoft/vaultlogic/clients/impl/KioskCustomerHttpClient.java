package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskCustomer;
import com.skysoft.vaultlogic.clients.api.model.Customer;
import com.skysoft.vaultlogic.clients.api.model.CustomerId;
import com.skysoft.vaultlogic.clients.api.model.CustomerIdPhone;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.skysoft.vaultlogic.clients.RequestFactory.post;
import static com.skysoft.vaultlogic.clients.api.model.CustomerPhone.of;
import static io.vavr.API.Try;

@Service
@AllArgsConstructor
public class KioskCustomerHttpClient implements KioskCustomer {

    private final MayaProperties maya;
    private final OAuth2RestTemplate rest;

    private <T> ResponseEntity<T> exchange(RequestEntity<?> request, Class<T> responseType) {
        return rest.exchange(request, responseType);
    }

    @Override
    public Either<Throwable, CustomerId> setCustomer(String xToken, String phoneNumber) {
        return Try(() -> exchange(post(xToken, maya::setCustomerInSessionURI, of(phoneNumber)), CustomerId.class))
                .map(getBody())
                .toEither();
    }

    @Override
    public Either<Throwable, Customer> getCustomer(String xToken, CustomerIdPhone customerIdPhone) {
        return Try(() -> exchange(post(xToken, maya::customerInformationURI, customerIdPhone), Customer.class))
                .map(getBody())
                .toEither();
    }

    private <T> Function<ResponseEntity<T>, T> getBody() {
        return ResponseEntity::getBody;
    }

}