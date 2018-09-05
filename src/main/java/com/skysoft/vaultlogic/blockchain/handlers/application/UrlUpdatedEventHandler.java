package com.skysoft.vaultlogic.blockchain.handlers.application;

import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage;
import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.ApplicationUrlUpdatedEventResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.core.methods.request.EthFilter;

import static com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.APPLICATIONURLUPDATED_EVENT;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
public class UrlUpdatedEventHandler {

    @Autowired
    public UrlUpdatedEventHandler(ApplicationStorage repository) {
        repository.applicationUrlUpdatedEventObservable(filter(repository)).subscribe(this::onNext, this::onError);
    }

    private EthFilter filter(ApplicationStorage repo) {
        return new EthFilter(LATEST, LATEST, repo.getContractAddress().substring(2))
                .addSingleTopic(EventEncoder.encode(APPLICATIONURLUPDATED_EVENT));
    }

    private void onNext(ApplicationUrlUpdatedEventResponse event) {
        log.info("[x] Application url updated event. {}, {}", event.appId, event.url);
    }

    private void onError(Throwable throwable) {
        log.error("[x] Error handle ApplicationUrlUpdated event", throwable);
    }

}
