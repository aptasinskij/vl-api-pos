package com.skysoft.vaultlogic.blockchain.handlers.local.storage.session;

import com.skysoft.vaultlogic.blockchain.contracts.SessionStorage;
import com.skysoft.vaultlogic.blockchain.contracts.SessionStorage.SavedEventResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.core.methods.request.EthFilter;

import static com.skysoft.vaultlogic.blockchain.contracts.SessionStorage.SAVED_EVENT;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
@Profile("ganache")
public class SessionSavedEventHandler {

    @Autowired
    public SessionSavedEventHandler(SessionStorage sessionStorage) {
        sessionStorage.savedEventObservable(buildFilter(sessionStorage))
                .subscribe(this::onNext, this::onError);
    }

    private EthFilter buildFilter(SessionStorage sessionStorage) {
        return new EthFilter(LATEST, LATEST, sessionStorage.getContractAddress().substring(2))
                .addSingleTopic(EventEncoder.encode(SAVED_EVENT));
    }

    private void onNext(SavedEventResponse event) {
        log.info("[x] Session saved: {}, {}", event.sessionId, event.xToken);
    }

    private void onError(Throwable throwable) {
        log.error("[x] Error filtering Session Saved Event.", throwable);
    }


}
