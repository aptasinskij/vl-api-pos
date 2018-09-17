package com.skysoft.vaultlogic.blockchain.handlers.storages.local.session;

import com.skysoft.vaultlogic.blockchain.contracts.SessionStorage;
import com.skysoft.vaultlogic.blockchain.contracts.SessionStorage.StatusUpdatedEventResponse;
import com.skysoft.vaultlogic.blockchain.handlers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.blockchain.handlers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import java.util.function.Function;

import static com.skysoft.vaultlogic.blockchain.contracts.SessionStorage.STATUSUPDATED_EVENT;

@Slf4j
@Component
@Profile("ganache")
public class SessionStatusUpdatedEventObserver extends AbstractContractEventObserver<StatusUpdatedEventResponse, SessionStorage> {

    @Autowired
    public SessionStatusUpdatedEventObserver(SessionStorage sessionStorage) {
        super(sessionStorage);
    }

    @Override
    protected Event eventToFilterFor() {
        return STATUSUPDATED_EVENT;
    }

    @Override
    protected EventObservable<StatusUpdatedEventResponse> getEventObservable() {
        return contract::statusUpdatedEventObservable;
    }

    @Override
    protected Function<SessionStorage, String> getAddress() {
        return contract -> contract.getContractAddress().substring(2);
    }

    public void onNext(StatusUpdatedEventResponse event) {
        log.info("[x] Session status updated: {}, {}", event.index, event.status);
    }

    public void onError(Throwable throwable) {
        log.error("[x] Error filtering Session Status Updated Event.", throwable);
    }

}
