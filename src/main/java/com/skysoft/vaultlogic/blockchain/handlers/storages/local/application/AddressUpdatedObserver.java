package com.skysoft.vaultlogic.blockchain.handlers.storages.local.application;

import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage;
import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.ApplicationAddressUpdatedEventResponse;
import com.skysoft.vaultlogic.blockchain.handlers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.blockchain.handlers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import java.util.function.Function;

import static com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.APPLICATIONADDRESSUPDATED_EVENT;

@Slf4j
@Component
@Profile("ganache")
public class AddressUpdatedObserver extends AbstractContractEventObserver<ApplicationAddressUpdatedEventResponse, ApplicationStorage> {

    @Autowired
    public AddressUpdatedObserver(ApplicationStorage applicationStorage) {
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
    protected Function<ApplicationStorage, String> getAddress() {
        return contract -> contract.getContractAddress().substring(2);
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