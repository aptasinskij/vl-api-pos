package com.skysoft.vaultlogic.observers.storages.ganache.application;

import com.skysoft.vaultlogic.contracts.ApplicationStorage;
import com.skysoft.vaultlogic.contracts.ApplicationStorage.ApplicationSavedEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import java.util.function.Function;

import static com.skysoft.vaultlogic.contracts.ApplicationStorage.APPLICATIONSAVED_EVENT;

@Slf4j
@Component
@Profile("ganache")
public class SavedEventObserver extends AbstractContractEventObserver<ApplicationSavedEventResponse, ApplicationStorage> {

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
    protected Function<ApplicationStorage, String> getAddress() {
        return contract -> contract.getContractAddress().substring(2);
    }

    @Override
    public void onNext(ApplicationSavedEventResponse event) {
        log.info("[x] Application saved : {}", event.appId);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error handler ApplicationSaved event", throwable);
    }

}
