package com.skysoft.vaultlogic.web.dbdata;

import com.skysoft.vaultlogic.common.domain.application.Application;
import com.skysoft.vaultlogic.common.domain.user.User;
import com.skysoft.vaultlogic.common.domain.user.UserRepository;
import com.skysoft.vaultlogic.web.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ApplicationService applicationService;

    @Autowired
    public DatabaseData(UserRepository userRepository, ApplicationService applicationService) {
        this.userRepository = userRepository;
        this.applicationService = applicationService;
    }

    @Override
    public void run(String... args) throws Exception {
        User appOwner = userRepository.save(getStubUser());
        Application application = getApplication(appOwner);
        applicationService.registerApplication(application);
    }

    private Application getApplication(User appOwner) {
        return Application.newApplication("CapitalHero", "http://localhost.com", appOwner, "0x04d3d07229557903459acadd33f5e505f928bd3c");
    }

    private User getStubUser() {
        return User.newUser("aptasinskij", "secret", "0xbEf2b0D2e5C33710c7ECa40983960D52e79D21d1");
    }

}
