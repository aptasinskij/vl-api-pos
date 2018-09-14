package com.skysoft.vaultlogic.blockchain.handlers.local.storage.application;

import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage;
import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.ApplicationStatusUpdatedEventResponse;
import com.skysoft.vaultlogic.blockchain.handlers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.blockchain.handlers.api.EventObservable;
import com.skysoft.vaultlogic.common.domain.application.Application.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.DefaultBlockParameterName;

import java.util.function.Function;

import static com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.APPLICATIONSTATUSUPDATED_EVENT;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
@Profile("ganache")
public class StatusUpdatedEventObserver extends AbstractContractEventObserver<ApplicationStatusUpdatedEventResponse, ApplicationStorage> {

    @Autowired
    protected StatusUpdatedEventObserver(ApplicationStorage applicationStorage) {
        super(applicationStorage);
    }

    @Override
    protected Event getEvent() {
        return APPLICATIONSTATUSUPDATED_EVENT;
    }

    @Override
    protected EventObservable<ApplicationStatusUpdatedEventResponse> getObservable() {
        return contract::applicationStatusUpdatedEventObservable;
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
    public void onNext(ApplicationStatusUpdatedEventResponse event) {
        log.info("[x] Application status updated: {}, {}", event.appId, Status.from(event.status.intValue()));
    }

    @Override
    public void onCompleted() {
        log.info("[x] Application status updated events completed");
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error handler ApplicationStatusUpdated event", throwable);
    }

}
