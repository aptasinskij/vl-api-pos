package com.skysoft.vaultlogic.blockchain.handlers.application;

import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage;
import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.ApplicationSavedEventResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.core.methods.request.EthFilter;

import static com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.APPLICATIONSAVED_EVENT;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
public class SavedEventHandler {

    @Autowired
    public SavedEventHandler(ApplicationStorage applicationStorage) {
        applicationStorage.applicationSavedEventObservable(buildFilter(applicationStorage))
                .subscribe(this::onNext, this::onError);
    }

    private EthFilter buildFilter(ApplicationStorage applicationStorage) {
        return new EthFilter(LATEST, LATEST, applicationStorage.getContractAddress().substring(2))
                .addSingleTopic(EventEncoder.encode(APPLICATIONSAVED_EVENT));
    }

    private void onNext(ApplicationSavedEventResponse event) {
        log.info("[x] Application saved : {}", event.appId);
    }

    private void onError(Throwable throwable) {
        log.error("[x] Error handler ApplicationSaved event", throwable);
    }

}
