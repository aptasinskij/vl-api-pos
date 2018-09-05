package com.skysoft.vaultlogic.web.handler;

import com.skysoft.vaultlogic.common.domain.application.Application;
import com.skysoft.vaultlogic.common.domain.user.User;
import com.skysoft.vaultlogic.common.domain.user.UserRepository;
import com.skysoft.vaultlogic.web.service.JpaApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CMD implements CommandLineRunner {

    private final JpaApplicationService jpaApplicationService;
    private final UserRepository userRepo;

    private static final String USER_ADDRESS = "0xbEf2b0D2e5C33710c7ECa40983960D52e79D21d1";
    private static final String APP_ADDRESS = "0x50316Ad975eE1Be157d54663F82a216CC6349453";

    @Autowired
    public CMD(JpaApplicationService jpaApplicationService, UserRepository userRepo) {
        this.jpaApplicationService = jpaApplicationService;
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        User owner = userRepo.save(User.newUser("aptasinskij", "analogue", USER_ADDRESS));
        Application capitalHero = Application.newApplication("CapitalHero", "http://localhost.com", owner, APP_ADDRESS);
        jpaApplicationService.registerApplication(capitalHero);
    }

}
