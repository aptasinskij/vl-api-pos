package com.skysoft.vaultlogic.blockchain.handlers.cloud.storage.application;

import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage;
import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.ApplicationUrlUpdatedEventResponse;
import com.skysoft.vaultlogic.blockchain.handlers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.blockchain.handlers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.tx.Contract;

import java.util.function.Function;

import static com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.APPLICATIONURLUPDATED_EVENT;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
@Profile("cloud-quorum")
public class UrlUpdatedEventObserver extends AbstractContractEventObserver<ApplicationUrlUpdatedEventResponse, ApplicationStorage> {

    @Autowired
    public UrlUpdatedEventObserver(ApplicationStorage applicationStorage) {
        super(applicationStorage);
    }

    @Override
    protected Event getEvent() {
        return APPLICATIONURLUPDATED_EVENT;
    }

    @Override
    protected EventObservable<ApplicationUrlUpdatedEventResponse> getObservable() {
        return contract::applicationUrlUpdatedEventObservable;
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
        return Contract::getContractAddress;
    }

    @Override
    public void onNext(ApplicationUrlUpdatedEventResponse event) {
        log.info("[x] Application url updated event. {}, {}", event.appId, event.url);
    }

    @Override
    public void onCompleted() {
        log.info("[x] Application url update completed.");
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error handle ApplicationUrlUpdated event", throwable);
    }

}
