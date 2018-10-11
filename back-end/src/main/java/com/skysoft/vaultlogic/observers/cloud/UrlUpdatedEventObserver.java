package com.skysoft.vaultlogic.observers.cloud;

import com.skysoft.vaultlogic.contracts.ApplicationStorage;
import com.skysoft.vaultlogic.contracts.ApplicationStorage.ApplicationUrlUpdatedEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import static com.skysoft.vaultlogic.contracts.ApplicationStorage.APPLICATIONURLUPDATED_EVENT;

@Slf4j
@Component
@Profile("cloud")
public class UrlUpdatedEventObserver extends AbstractContractEventObserver<ApplicationUrlUpdatedEventResponse, ApplicationStorage> {

    @Autowired
    public UrlUpdatedEventObserver(ApplicationStorage applicationStorage) {
        super(applicationStorage);
        subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return APPLICATIONURLUPDATED_EVENT;
    }

    @Override
    protected EventObservable<ApplicationUrlUpdatedEventResponse> getEventObservable() {
        return contract::applicationUrlUpdatedEventObservable;
    }

    @Override
    public void onNext(ApplicationUrlUpdatedEventResponse event) {
        log.info("[x] Application url updated event. {}, {}", event.appId, event.url);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error handle ApplicationUrlUpdated event: {}", throwable.getMessage());
    }

}
