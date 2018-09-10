package com.skysoft.vaultlogic.blockchain.handlers.local.storage;

import com.skysoft.vaultlogic.blockchain.contracts.SessionStorage;
import com.skysoft.vaultlogic.blockchain.contracts.SessionStorage.StatusUpdatedEventResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.core.methods.request.EthFilter;

import static com.skysoft.vaultlogic.blockchain.contracts.SessionStorage.STATUSUPDATED_EVENT;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
public class SessionStatusUpdatedEventHandler {

    @Autowired
    public SessionStatusUpdatedEventHandler(SessionStorage sessionStorage) {
        sessionStorage.statusUpdatedEventObservable(buildFilter(sessionStorage))
                .subscribe(this::onNext, this::onError);
    }

    private EthFilter buildFilter(SessionStorage sessionStorage) {
        return new EthFilter(LATEST, LATEST, sessionStorage.getContractAddress().substring(2))
                .addSingleTopic(EventEncoder.encode(STATUSUPDATED_EVENT));
    }

    private void onNext(StatusUpdatedEventResponse event) {
        log.info("[x] Session status updated: {}, {}", event.index, event.status);
    }

    private void onError(Throwable throwable) {
        log.error("[x] Error filtering Session Status Updated Event.", throwable);
    }

}
