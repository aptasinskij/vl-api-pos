package com.skysoft.vaultlogic.blockchain.handlers.application;

import com.skysoft.vaultlogic.blockchain.contracts.ApplicationRepository;
import com.skysoft.vaultlogic.blockchain.contracts.ApplicationRepository.ApplicationStatusUpdatedEventResponse;
import com.skysoft.vaultlogic.common.domain.application.Application.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.core.methods.request.EthFilter;

import static com.skysoft.vaultlogic.blockchain.contracts.ApplicationRepository.APPLICATIONSTATUSUPDATED_EVENT;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
public class StatusUpdatedEventHandler {

    @Autowired
    protected StatusUpdatedEventHandler(ApplicationRepository applicationRepository) {
        applicationRepository.applicationStatusUpdatedEventObservable(buildFilter(applicationRepository))
                .subscribe(this::onNext, this::onError);
    }

    private EthFilter buildFilter(ApplicationRepository applicationRepository) {
        return new EthFilter(LATEST, LATEST, applicationRepository.getContractAddress().substring(2))
                .addSingleTopic(EventEncoder.encode(APPLICATIONSTATUSUPDATED_EVENT));
    }

    private void onNext(ApplicationStatusUpdatedEventResponse event) {
        log.info("[x] Application status updated: {}, {}", event.appId, Status.from(event.status.intValue()));
    }

    private void onError(Throwable throwable) {
        log.error("[x] Error handler ApplicationStatusUpdated event", throwable);
    }

}
