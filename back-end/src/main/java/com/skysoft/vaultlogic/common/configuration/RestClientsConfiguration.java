package com.skysoft.vaultlogic.common.configuration;

import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("cloud")
@AllArgsConstructor
public class RestClientsConfiguration {

    private final MayaProperties mayaProperties;

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setAccessTokenUri(mayaProperties.getSso());
        resourceDetails.setClientId(mayaProperties.getAccess().getClientId());
        resourceDetails.setClientSecret(mayaProperties.getAccess().getClientSecret());
        return new OAuth2RestTemplate(resourceDetails, new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest()));
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
