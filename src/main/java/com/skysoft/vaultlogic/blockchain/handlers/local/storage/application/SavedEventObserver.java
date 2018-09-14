package com.skysoft.vaultlogic.blockchain.handlers.local.storage.application;

import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage;
import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.ApplicationSavedEventResponse;
import com.skysoft.vaultlogic.blockchain.handlers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.blockchain.handlers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.DefaultBlockParameterName;

import java.util.function.Function;

import static com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.APPLICATIONSAVED_EVENT;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
@Profile("ganache")
public class SavedEventObserver extends AbstractContractEventObserver<ApplicationSavedEventResponse, ApplicationStorage> {


    public SavedEventObserver(ApplicationStorage applicationStorage) {
        super(applicationStorage);
    }

    @Override
    protected Event getEvent() {
        return APPLICATIONSAVED_EVENT;
    }

    @Override
    protected EventObservable<ApplicationSavedEventResponse> getObservable() {
        return contract::applicationSavedEventObservable;
    }

    @Override
    protected DefaultBlockParameterName getFromBlock() {
        return LATEST;
    }

    @Override
    protected DefaultBlockParameterName getToBlock() {
        return LATEST;
    }

    @Override
    protected Function<ApplicationStorage, String> getAddressFunction() {
        return contract -> contract.getContractAddress().substring(2);
    }

    @Override
    public void onNext(ApplicationSavedEventResponse event) {
        log.info("[x] Application saved : {}", event.appId);
    }

    @Override
    public void onCompleted() {
        log.info("[x] Application saved events completed");
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error handler ApplicationSaved event", throwable);
    }

}
