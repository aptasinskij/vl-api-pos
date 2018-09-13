package com.skysoft.vaultlogic.blockchain.handlers.local.storage.application;

import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage;
import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.ApplicationStatusUpdatedEventResponse;
import com.skysoft.vaultlogic.blockchain.handlers.api.ApplicationStorageEventObserver;
import com.skysoft.vaultlogic.common.domain.application.Application.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.core.methods.request.EthFilter;

import static com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.APPLICATIONSTATUSUPDATED_EVENT;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
@Profile("ganache")
public class StatusUpdatedEventObserver implements ApplicationStorageEventObserver<ApplicationStatusUpdatedEventResponse> {

    @Autowired
    protected StatusUpdatedEventObserver(ApplicationStorage applicationStorage) {
        applicationStorage.applicationStatusUpdatedEventObservable(buildFilter(applicationStorage))
                .subscribe(this::onNext, this::onError);
    }

    private EthFilter buildFilter(ApplicationStorage applicationStorage) {
        return new EthFilter(LATEST, LATEST, applicationStorage.getContractAddress().substring(2))
                .addSingleTopic(EventEncoder.encode(APPLICATIONSTATUSUPDATED_EVENT));
    }

    public void onNext(ApplicationStatusUpdatedEventResponse event) {
        log.info("[x] Application status updated: {}, {}", event.appId, Status.from(event.status.intValue()));
    }

    @Override
    public void onCompleted() {

    }

    public void onError(Throwable throwable) {
        log.error("[x] Error handler ApplicationStatusUpdated event", throwable);
    }

}
