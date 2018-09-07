package com.skysoft.vaultlogic.common.listeners;

import com.skysoft.vaultlogic.blockchain.service.BlockchainAppService;
import com.skysoft.vaultlogic.common.domain.application.ApplicationRepository;
import com.skysoft.vaultlogic.common.domain.application.events.AppRegistered;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class AppDomainEventsListener {

    private final ApplicationRepository applicationRepository;
    private final BlockchainAppService blockchainAppService;

    @Autowired
    public AppDomainEventsListener(ApplicationRepository applicationRepository, BlockchainAppService blockchainAppService) {
        this.applicationRepository = applicationRepository;
        this.blockchainAppService = blockchainAppService;
    }

    @Async
    @TransactionalEventListener
    public void handleAppRegisteredEvent(AppRegistered event) {
        log.info("[x]---> Handling application registered event for app with name: {}", event.name);
        blockchainAppService.saveApplication(applicationRepository.findSmartContractApplicationByName(event.name));
    }

}
