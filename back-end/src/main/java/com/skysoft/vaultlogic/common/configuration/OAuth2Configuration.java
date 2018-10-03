package com.skysoft.vaultlogic.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@Configuration
public class OAuth2Configuration {

    private static final String CLIENT_ID = "zlJLS7b3AdoCzwj4eQYvQ2K7x3KvDJlBKf3yqTPu";
    private static final String CLIENT_SECRET = "wVgM1llhuNzTHTCU3NCZ9xpmz3Xmfn9VlRAPNNlu9sBo1A427gVtNLp3e8b3or1kqXwbSjlSXLQtg8nAMqfqBVNcFbV2IfiERI9vlKiAfkaeL6oLklVmkuBgujIstwSJ";
    private static final String MAYA_AUTHORIZATION_URL = "https://sso.maya.tech/oauth2/token";

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setAccessTokenUri(MAYA_AUTHORIZATION_URL);
        resourceDetails.setClientId(CLIENT_ID);
        resourceDetails.setClientSecret(CLIENT_SECRET);
        return new OAuth2RestTemplate(resourceDetails, new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest()));
    }

}
