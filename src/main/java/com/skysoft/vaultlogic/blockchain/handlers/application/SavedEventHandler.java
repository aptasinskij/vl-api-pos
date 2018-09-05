package com.skysoft.vaultlogic.blockchain.handlers.application;

import com.skysoft.vaultlogic.blockchain.contracts.ApplicationRepository;
import com.skysoft.vaultlogic.blockchain.contracts.ApplicationRepository.ApplicationSavedEventResponse;
import com.skysoft.vaultlogic.common.domain.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.core.methods.request.EthFilter;

import static com.skysoft.vaultlogic.blockchain.contracts.ApplicationRepository.APPLICATIONSAVED_EVENT;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
public class SavedEventHandler {

    @Autowired
    public SavedEventHandler(ApplicationRepository applicationRepository) {
        applicationRepository.applicationSavedEventObservable(buildFilter(applicationRepository))
                .subscribe(this::onNext, this::onError);
    }

    private EthFilter buildFilter(ApplicationRepository applicationRepository) {
        return new EthFilter(LATEST, LATEST, applicationRepository.getContractAddress().substring(2))
                .addSingleTopic(EventEncoder.encode(APPLICATIONSAVED_EVENT));
    }

    private void onNext(ApplicationSavedEventResponse event) {
        log.info("[x] Application saved : {}", event.appId);
    }

    private void onError(Throwable throwable) {
        log.error("[x] Error handler ApplicationSaved event", throwable);
    }

}
