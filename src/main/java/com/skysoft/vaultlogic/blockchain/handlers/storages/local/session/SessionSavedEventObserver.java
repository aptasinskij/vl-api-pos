package com.skysoft.vaultlogic.blockchain.handlers.storages.local.session;

import com.skysoft.vaultlogic.blockchain.contracts.SessionStorage;
import com.skysoft.vaultlogic.blockchain.contracts.SessionStorage.SavedEventResponse;
import com.skysoft.vaultlogic.blockchain.handlers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.blockchain.handlers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import java.util.function.Function;

import static com.skysoft.vaultlogic.blockchain.contracts.SessionStorage.SAVED_EVENT;

@Slf4j
@Component
@Profile("ganache")
public class SessionSavedEventObserver extends AbstractContractEventObserver<SavedEventResponse, SessionStorage> {

    @Autowired
    public SessionSavedEventObserver(SessionStorage sessionStorage) {
        super(sessionStorage);
        subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return SAVED_EVENT;
    }

    @Override
    protected EventObservable<SavedEventResponse> getEventObservable() {
        return contract::savedEventObservable;
    }

    @Override
    protected Function<SessionStorage, String> getAddress() {
        return contract -> contract.getContractAddress().substring(2);
    }

    public void onNext(SavedEventResponse event) {
        log.info("[x] Session saved: {}, {}", event.sessionId, event.xToken);
    }

    public void onError(Throwable throwable) {
        log.error("[x] Error filtering Session Saved Event.", throwable);
    }

}
