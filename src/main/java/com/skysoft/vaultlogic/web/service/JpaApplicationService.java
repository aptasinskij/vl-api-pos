package com.skysoft.vaultlogic.web.service;

import com.skysoft.vaultlogic.common.domain.application.Application;
import com.skysoft.vaultlogic.common.domain.application.ApplicationRepository;
import com.skysoft.vaultlogic.common.domain.application.projections.AppUriStatus;
import com.skysoft.vaultlogic.common.domain.application.projections.SmartContractApplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;


@Service
public class JpaApplicationService implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    public JpaApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Transactional
    public Application registerApplication(Application application) {
        application.submitApplication();
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
    @Transactional(readOnly = true)
    public SmartContractApplication findSCProjectionByName(String name) {
        return applicationRepository.findSmartContractApplicationByName(name);
        //TODO create meaningful exception
    }

    @Override
    public URI getApplicationUriIfEnabled(Long appId) {
        return applicationRepository.findAppUriStatusById(appId)
                .filter(AppUriStatus.enabled())
                .map(AppUriStatus::getUri)
                .map(URI::create)
                .orElseThrow(RuntimeException::new);
        //TODO create meaningful exception
    }

}
