package com.skysoft.vaultlogic.observers.storages.quorum.application;

import com.skysoft.vaultlogic.contracts.ApplicationStorage;
import com.skysoft.vaultlogic.contracts.ApplicationStorage.ApplicationStatusUpdatedEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import com.skysoft.vaultlogic.common.domain.application.Application.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import static com.skysoft.vaultlogic.contracts.ApplicationStorage.APPLICATIONSTATUSUPDATED_EVENT;

@Slf4j
@Component
@Profile("cloud-quorum")
public class StatusUpdatedEventObserver extends AbstractContractEventObserver<ApplicationStatusUpdatedEventResponse, ApplicationStorage> {

    @Autowired
    protected StatusUpdatedEventObserver(ApplicationStorage applicationStorage) {
        super(applicationStorage);
        subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return APPLICATIONSTATUSUPDATED_EVENT;
    }

    @Override
    protected EventObservable<ApplicationStatusUpdatedEventResponse> getEventObservable() {
        return contract::applicationStatusUpdatedEventObservable;
    }

    @Override
    public void onNext(ApplicationStatusUpdatedEventResponse event) {
        log.info("[x] Application status updated: {}, {}", event.appId, Status.from(event.status.intValue()));
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error handler ApplicationStatusUpdated event: {}", throwable.getMessage());
    }

}
