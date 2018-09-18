package com.skysoft.vaultlogic.blockchain.handlers.storages.quorum.application;

import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage;
import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.ApplicationSavedEventResponse;
import com.skysoft.vaultlogic.blockchain.handlers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.blockchain.handlers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import static com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.APPLICATIONSAVED_EVENT;

@Slf4j
@Component
@Profile("cloud-quorum")
public class SavedEventObserver extends AbstractContractEventObserver<ApplicationSavedEventResponse, ApplicationStorage> {

    @Autowired
    public SavedEventObserver(ApplicationStorage applicationStorage) {
        super(applicationStorage);
        subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return APPLICATIONSAVED_EVENT;
    }

    @Override
    protected EventObservable<ApplicationSavedEventResponse> getEventObservable() {
        return contract::applicationSavedEventObservable;
    }

    @Override
    public void onNext(ApplicationSavedEventResponse event) {
        log.info("[x] Application saved : {}", event.appId);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error handler ApplicationSaved event: {}", throwable.getMessage());
    }

}