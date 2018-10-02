package com.skysoft.vaultlogic.common.configuration;

import com.skysoft.vaultlogic.common.domain.application.Application;
import com.skysoft.vaultlogic.common.domain.user.User;
import com.skysoft.vaultlogic.common.domain.user.UserRepository;
import com.skysoft.vaultlogic.contracts.CapitalHero;
import com.skysoft.vaultlogic.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ApplicationService applicationService;
    private final CapitalHero capitalHero;

    private static final String APPLICATION_NAME = "CapitalHero";
    private static final String APPLICATION_URL = "http://localhost:9090/index";

    @Autowired
    public DatabaseData(UserRepository userRepository, ApplicationService applicationService, CapitalHero capitalHero) {
        this.userRepository = userRepository;
        this.applicationService = applicationService;
        this.capitalHero = capitalHero;
    }

    @Override
    public void run(String... args) throws Exception {
        User appOwner = userRepository.save(getStubUser());
        Application application = getApplication(appOwner);
        applicationService.registerApplication(application);
    }

    private Application getApplication(User appOwner) {
        return Application.newApplication(APPLICATION_NAME, APPLICATION_URL, appOwner, capitalHero.getContractAddress());
    }

    private User getStubUser() {
        return User.newUser("aptasinskij", "secret", "0xbEf2b0D2e5C33710c7ECa40983960D52e79D21d1");
    }

}
