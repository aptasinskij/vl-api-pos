package com.skysoft.vaultlogic.observers.storages.ganache.application;

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

import java.util.function.Function;

import static com.skysoft.vaultlogic.contracts.ApplicationStorage.APPLICATIONSTATUSUPDATED_EVENT;

@Slf4j
@Component
@Profile("ganache")
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
    protected Function<ApplicationStorage, String> getAddress() {
        return contract -> contract.getContractAddress().substring(2);
    }

    @Override
    public void onNext(ApplicationStatusUpdatedEventResponse event) {
        log.info("[x] Application status updated: {}, {}", event.appId, Status.from(event.status));
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error handler ApplicationStatusUpdated event", throwable);
    }

}
