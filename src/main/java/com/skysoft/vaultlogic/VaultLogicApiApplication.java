package com.skysoft.vaultlogic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication(exclude = {OAuth2AutoConfiguration.class, SecurityAutoConfiguration.class})
public class VaultLogicApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VaultLogicApiApplication.class, args);
    }
}
