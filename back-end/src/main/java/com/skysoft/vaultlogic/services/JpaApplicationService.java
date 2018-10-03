package com.skysoft.vaultlogic.services;

import com.skysoft.vaultlogic.common.domain.application.Application;
import com.skysoft.vaultlogic.common.domain.application.ApplicationRepository;
import com.skysoft.vaultlogic.common.domain.application.projections.AppUriStatus;
import com.skysoft.vaultlogic.common.domain.application.projections.SmartContractApplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.net.URI;


@Service
public class JpaApplicationService implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    public JpaApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Transactional
    public Application registerApplication(Application application) {
        application.publishCreated();
        return applicationRepository.save(application);
    }

    @Override
    @Transactional(readOnly = true)
    public Application findByName(String name) {
        return applicationRepository.findByName(name)
                .orElseThrow(RuntimeException::new);
        //TODO create meaningful exception
    }

    @Override
    public URI getApplicationUri(BigInteger applicationId) {
        return applicationRepository.findAppUriStatusById(applicationId)
                .map(AppUriStatus::getUri)
                .map(URI::create)
                .orElseThrow(RuntimeException::new);
        //TODO create meaningful exception
    }

}
