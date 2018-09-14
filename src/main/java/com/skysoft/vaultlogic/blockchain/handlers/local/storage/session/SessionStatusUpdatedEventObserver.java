package com.skysoft.vaultlogic.blockchain.handlers.local.storage.session;

import com.skysoft.vaultlogic.blockchain.contracts.SessionStorage;
import com.skysoft.vaultlogic.blockchain.contracts.SessionStorage.StatusUpdatedEventResponse;
import com.skysoft.vaultlogic.blockchain.handlers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.blockchain.handlers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.DefaultBlockParameterName;

import java.util.function.Function;

import static com.skysoft.vaultlogic.blockchain.contracts.SessionStorage.STATUSUPDATED_EVENT;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
@Profile("ganache")
public class SessionStatusUpdatedEventObserver extends AbstractContractEventObserver<StatusUpdatedEventResponse, SessionStorage> {

    @Autowired
    public SessionStatusUpdatedEventObserver(SessionStorage sessionStorage) {
        super(sessionStorage);
    }

    @Override
    protected Event getEvent() {
        return STATUSUPDATED_EVENT;
    }

    @Override
    protected EventObservable<StatusUpdatedEventResponse> getObservable() {
        return contract::statusUpdatedEventObservable;
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
    protected Function<SessionStorage, String> getAddressFunction() {
        return contract -> contract.getContractAddress().substring(2);
    }

    public void onNext(StatusUpdatedEventResponse event) {
        log.info("[x] Session status updated: {}, {}", event.index, event.status);
    }

    @Override
    public void onCompleted() {
        log.info("[x] Session status update completed.");
    }

    public void onError(Throwable throwable) {
        log.error("[x] Error filtering Session Status Updated Event.", throwable);
    }

}
