package com.skysoft.vaultlogic.observers.storages.quorum.application;

import com.skysoft.vaultlogic.contracts.ApplicationStorage;
import com.skysoft.vaultlogic.contracts.ApplicationStorage.ApplicationAddressUpdatedEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import static com.skysoft.vaultlogic.contracts.ApplicationStorage.APPLICATIONADDRESSUPDATED_EVENT;

@Slf4j
@Component
@Profile("cloud-quorum")
public class AddressUpdatedEventObserver extends AbstractContractEventObserver<ApplicationAddressUpdatedEventResponse, ApplicationStorage> {

    @Autowired
    public AddressUpdatedEventObserver(ApplicationStorage applicationStorage) {
        super(applicationStorage);
        subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return APPLICATIONADDRESSUPDATED_EVENT;
    }

    @Override
    protected EventObservable<ApplicationAddressUpdatedEventResponse> getEventObservable() {
        return contract::applicationAddressUpdatedEventObservable;
    }

    @Override
    public void onNext(ApplicationAddressUpdatedEventResponse event) {
        log.info("[x] Application address updated : {}, {}", event.appId, event.appAddr);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error handler ApplicationSaved event: {}", throwable.getMessage());
    }

}
