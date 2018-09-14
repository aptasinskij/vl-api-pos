package com.skysoft.vaultlogic.blockchain.handlers.cloud.storage.session;

import com.skysoft.vaultlogic.blockchain.contracts.SessionStorage;
import com.skysoft.vaultlogic.blockchain.contracts.SessionStorage.SavedEventResponse;
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

import static com.skysoft.vaultlogic.blockchain.contracts.SessionStorage.SAVED_EVENT;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
@Profile("cloud-quorum")
public class SessionSavedEventObserver extends AbstractContractEventObserver<SavedEventResponse, SessionStorage> {

    @Autowired
    public SessionSavedEventObserver(SessionStorage sessionStorage) {
        super(sessionStorage);
    }

    @Override
    protected Event getEvent() {
        return SAVED_EVENT;
    }

    @Override
    protected EventObservable<SavedEventResponse> getObservable() {
        return contract::savedEventObservable;
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
        return Contract::getContractAddress;
    }

    public void onNext(SavedEventResponse event) {
        log.info("[x] Session saved: {}, {}", event.sessionId, event.xToken);
    }

    @Override
    public void onCompleted() {
        log.info("[x] Session save completed.");
    }

    public void onError(Throwable throwable) {
        log.error("[x] Error filtering Session Saved Event.", throwable);
    }


}
