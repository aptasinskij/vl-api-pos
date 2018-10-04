package com.skysoft.vaultlogic.common.configuration;

import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@Configuration
@AllArgsConstructor
public class OAuth2Configuration {

    private final MayaProperties mayaProperties;
    private final MayaEnhancerRequestInterceptor mayaEnhancerRequestInterceptor;

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setAccessTokenUri(mayaProperties.getSso());
        resourceDetails.setClientId(mayaProperties.getAccess().getClientId());
        resourceDetails.setClientSecret(mayaProperties.getAccess().getClientSecret());
//        this.oAuth2RestTemplate().getInterceptors().add(mayaEnhancerRequestInterceptor);
        return new OAuth2RestTemplate(resourceDetails, new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest()));
    }

}
