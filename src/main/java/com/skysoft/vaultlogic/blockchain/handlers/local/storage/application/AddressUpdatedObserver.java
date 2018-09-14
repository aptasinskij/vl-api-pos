package com.skysoft.vaultlogic.blockchain.handlers.local.storage.application;

import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage;
import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.ApplicationAddressUpdatedEventResponse;
import com.skysoft.vaultlogic.blockchain.handlers.api.AbstractContractEventObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import rx.Observable;

import java.util.function.Function;

import static com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.APPLICATIONADDRESSUPDATED_EVENT;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
@Profile("ganache")
public class AddressUpdatedObserver extends AbstractContractEventObserver<ApplicationAddressUpdatedEventResponse, ApplicationStorage> {

    @Autowired
    public AddressUpdatedObserver(ApplicationStorage applicationStorage) {
        super(applicationStorage);
        applicationStorage.applicationAddressUpdatedEventObservable(buildFilter(applicationStorage)).subscribe(this);
    }

    //TODO accomplish abstraction

    @Override
    protected Event getEvent() {
        return APPLICATIONADDRESSUPDATED_EVENT;
    }

    @Override
    protected Function<EthFilter, Observable<ApplicationAddressUpdatedEventResponse>> getObservable() {
        return contract::applicationAddressUpdatedEventObservable;
    }

    @Override
    protected DefaultBlockParameterName getFromBlock() {
        return LATEST;
    }

    @Override
    protected DefaultBlockParameterName getToBlock() {
        return LATEST;
    }

    private EthFilter buildFilter(ApplicationStorage applicationStorage) {
        return new EthFilter(LATEST, LATEST, applicationStorage.getContractAddress().substring(2))
                .addSingleTopic(EventEncoder.encode(APPLICATIONADDRESSUPDATED_EVENT));
    }

    @Override
    public void onNext(ApplicationAddressUpdatedEventResponse event) {
        log.info("[x] Application address updated : {}, {}", event.appId, event.appAddr);
    }

    @Override
    public void onCompleted() {
        log.info("[x] Application address updated events completed");
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error handler ApplicationSaved event", throwable);
    }

}
