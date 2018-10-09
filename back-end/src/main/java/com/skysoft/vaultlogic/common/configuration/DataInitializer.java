package com.skysoft.vaultlogic.common.configuration;

import com.skysoft.vaultlogic.common.domain.application.Application;
import com.skysoft.vaultlogic.common.domain.user.User;
import com.skysoft.vaultlogic.common.domain.user.UserRepository;
import com.skysoft.vaultlogic.contracts.CapitalHero;
import com.skysoft.vaultlogic.contracts.ParameterStorage;
import com.skysoft.vaultlogic.services.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;

@Slf4j
@Configuration
public class DataInitializer {

    private final UserRepository userRepository;
    private final ApplicationService applicationService;
    private final CapitalHero capitalHero;

    private static final String APPLICATION_NAME = "CapitalHero";
    private static final String APPLICATION_URL = "http://192.168.15.200:8888";

    @Autowired
    public DataInitializer(UserRepository userRepository, ApplicationService applicationService, CapitalHero capitalHero) {
        this.userRepository = userRepository;
        this.applicationService = applicationService;
        this.capitalHero = capitalHero;
    }

    @Bean
    public CommandLineRunner registerUserAndApplication() {
        return args -> {
            User user = User.newUser("aptasinskij", "secret", "0xbEf2b0D2e5C33710c7ECa40983960D52e79D21d1");
            Application application = Application.newApplication(APPLICATION_NAME, APPLICATION_URL, user, capitalHero.getContractAddress());
            userRepository.save(user);
            applicationService.registerApplication(application);
        };
    }

    @Bean
    public CommandLineRunner setUpSmartContractParameters(ParameterStorage parameterStorage) {
        return args -> parameterStorage.setVLFee(BigInteger.valueOf(1000)).observable()
                .subscribe(next -> log.info("[x] VL FEE is set up"), error -> log.info("[x] Error set VL FEE"));
    }

}
