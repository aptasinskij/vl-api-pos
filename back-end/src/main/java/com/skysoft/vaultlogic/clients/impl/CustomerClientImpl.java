package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.CustomerClient;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import com.skysoft.vaultlogic.clients.mappers.CustomerMapper;
import com.skysoft.vaultlogic.clients.responseModels.customer.CustomerInfo;
import com.skysoft.vaultlogic.clients.responseModels.customer.CustomerSetInSessionInfo;
import com.skysoft.vaultlogic.clients.requests.customer.CustomerInfoRequest;
import com.skysoft.vaultlogic.clients.requests.customer.SetCustomerInSessionRequest;
import com.skysoft.vaultlogic.clients.requestModels.customer.CustomerInfoBody;
import com.skysoft.vaultlogic.clients.responces.customer.GetCustomerInformationResponse;
import com.skysoft.vaultlogic.clients.responces.customer.SetCustomerInSessionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.clients.MayaHeaders.X_TOKEN_HEADER;

@Service
public class CustomerClientImpl implements CustomerClient {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerClientImpl(MayaProperties mayaProperties,
                              OAuth2RestTemplate oAuth2RestTemplate,
                              CustomerMapper customerMapper) {
        this.mayaProperties = mayaProperties;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerSetInSessionInfo setCustomerInSession(String xToken, String phoneNumber) {
        try {
            ResponseEntity<SetCustomerInSessionResponse> exchange = oAuth2RestTemplate.exchange(buildSetCustomerInSessionRequestEntity(xToken, phoneNumber), SetCustomerInSessionResponse.class);
            return customerMapper.responseToCustomerInSessionInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public CustomerInfo getCustomerInformation(String xToken, CustomerInfoBody customerInfo) {
        try {
            ResponseEntity<GetCustomerInformationResponse> exchange = oAuth2RestTemplate.exchange(buildGetCustomerInfoRequestEntity(xToken, customerInfo), GetCustomerInformationResponse.class);
            return customerMapper.responseToCustomerInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    private RequestEntity<SetCustomerInSessionRequest> buildSetCustomerInSessionRequestEntity(String xToken, String phoneNumber) {
        return RequestEntity.post(URI.create(mayaProperties.getSetCustomerInSessionUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(SetCustomerInSessionRequest.of(phoneNumber));
    }

    private RequestEntity<CustomerInfoRequest> buildGetCustomerInfoRequestEntity(String xToken, CustomerInfoBody customerInfo) {
        return RequestEntity.post(URI.create(mayaProperties.getCustomerInformationUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(CustomerInfoRequest.of(customerInfo));
    }

}