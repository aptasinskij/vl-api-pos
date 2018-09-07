package com.skysoft.vaultlogic.common.listeners;

import com.skysoft.vaultlogic.blockchain.service.BlockchainAppService;
import com.skysoft.vaultlogic.common.domain.application.events.AppRegistered;
import com.skysoft.vaultlogic.web.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.NESTED;

@Slf4j
@Component
public class ApplicationDomainEventsListener {

    private final BlockchainAppService blockchainAppService;

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationDomainEventsListener(BlockchainAppService blockchainAppService, ApplicationService applicationService) {
        this.blockchainAppService = blockchainAppService;
        this.applicationService = applicationService;
    }

    @Async
    @TransactionalEventListener
    @Transactional(propagation = NESTED)
    public void registered(AppRegistered event) {
        log.info("[x]---> Handling application registered event for app with name: {}", event.name);
        blockchainAppService.saveApplication(applicationService.findSmartContractApplicationByName(event.name));
    }

}
