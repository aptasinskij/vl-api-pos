package com.skysoft.vaultlogic.listeners;

import com.skysoft.vaultlogic.common.domain.application.Application;
import com.skysoft.vaultlogic.common.domain.application.ApplicationRepository;
import com.skysoft.vaultlogic.common.domain.application.events.ApplicationCreated;
import com.skysoft.vaultlogic.contracts.ApplicationStorage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@Slf4j
@Component
@AllArgsConstructor
public class ApplicationDomainEventsListener {

    private final ApplicationStorage applicationStorage;
    private final ApplicationRepository applicationRepository;

    @Async
    @TransactionalEventListener
    @Transactional(readOnly = true)
    public void registered(ApplicationCreated event) {
        Application application = applicationRepository.findByNameJoinOwner(event.name).orElseThrow(RuntimeException::new);
        applicationStorage.save(application.getId(), application.getName(), application.getOwner().getAddress(), application.getUri(), application.getAddress(), application.getStatus().getValue())
                .observable()
                .subscribe(this::onNext, this::onError);
    }

    private void onNext(TransactionReceipt transactionReceipt) {
        log.info("[x] --> Application registered. TX: {}", transactionReceipt.getTransactionHash());
    }

    private void onError(Throwable throwable) {
        log.error("[x]---> Error during app registration. {}", throwable.getMessage());
    }

}
