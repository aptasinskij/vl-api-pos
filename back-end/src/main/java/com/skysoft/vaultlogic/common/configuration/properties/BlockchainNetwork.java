package com.skysoft.vaultlogic.common.configuration.properties;

import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Validated
@ConfigurationProperties("blockchain.network")
public class BlockchainNetwork {

    @NotBlank
    private String id;

    @URL
    @NotBlank
    private String url;

}
