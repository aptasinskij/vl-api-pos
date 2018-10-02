package com.skysoft.vaultlogic.web.maya.clients.impl;

import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import com.skysoft.vaultlogic.web.maya.MayaException;
import com.skysoft.vaultlogic.web.maya.clients.api.CustomerClient;
import com.skysoft.vaultlogic.web.maya.clients.responce.customer.GetCustomerInformationResponse;
import com.skysoft.vaultlogic.web.maya.clients.responce.customer.SetCustomerInSessionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.web.maya.MayaHeaders.getxTokenHeader;

@Service
public class CustomerClientImpl implements CustomerClient {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    public CustomerClientImpl(MayaProperties mayaProperties,
                              OAuth2RestTemplate oAuth2RestTemplate) {
        this.mayaProperties = mayaProperties;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    @Override
    public ResponseEntity<SetCustomerInSessionResponse> setCustomerInSession(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getSetCustomerInSessionUrl()), SetCustomerInSessionResponse.class);
    }

    @Override
    public ResponseEntity<GetCustomerInformationResponse> getCustomerInformation(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getCustomerInformationUrl()), GetCustomerInformationResponse.class);
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(getxTokenHeader(), xToken)
                .build();
    }

}