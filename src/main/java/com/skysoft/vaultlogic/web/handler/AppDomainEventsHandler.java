package com.skysoft.vaultlogic.web.handler;

import com.skysoft.vaultlogic.common.domain.application.ApplicationRepository;
import com.skysoft.vaultlogic.common.domain.application.events.AppRegistered;
import com.skysoft.vaultlogic.web.service.JpaApplicationService;
import com.skysoft.vaultlogic.blockchain.service.BlockchainAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class AppDomainEventsHandler implements AppEventsHandler {

    private final ApplicationRepository applicationRepository;
    private final JpaApplicationService jpaApplicationService;
    private final BlockchainAppService blockchainAppService;

    @Autowired
    public AppDomainEventsHandler(ApplicationRepository applicationRepository, JpaApplicationService jpaApplicationService, BlockchainAppService blockchainAppService) {
        this.applicationRepository = applicationRepository;
        this.jpaApplicationService = jpaApplicationService;
        this.blockchainAppService = blockchainAppService;
    }

    @Async
    @Override
    @TransactionalEventListener
    public void handleAppRegisteredEvent(AppRegistered event) {
        log.info("[x]---> Handling application registered event for app with name: {}", event.name);
    }

}
