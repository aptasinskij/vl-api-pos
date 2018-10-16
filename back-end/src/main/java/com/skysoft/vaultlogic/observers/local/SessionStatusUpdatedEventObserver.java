package com.skysoft.vaultlogic.observers.local;

import com.skysoft.vaultlogic.contracts.SessionStorage;
import com.skysoft.vaultlogic.contracts.SessionStorage.StatusUpdatedEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import static com.skysoft.vaultlogic.contracts.SessionStorage.STATUSUPDATED_EVENT;

@Slf4j
@Component
@Profile("local")
public class SessionStatusUpdatedEventObserver extends AbstractContractEventObserver<StatusUpdatedEventResponse, SessionStorage> {

    @Autowired
    public SessionStatusUpdatedEventObserver(SessionStorage sessionStorage) {
        super(sessionStorage);
        subscribe();
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
    public void onNext(StatusUpdatedEventResponse event) {
        log.info("[x] Session status updated: {}, {}", event.index, event.status);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering Session Status Updated Event.", throwable);
    }

}
