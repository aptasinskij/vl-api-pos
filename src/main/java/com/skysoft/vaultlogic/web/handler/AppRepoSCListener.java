package com.skysoft.vaultlogic.web.handler;

import com.skysoft.vaultlogic.blockchain.contracts.ApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.core.methods.request.EthFilter;

import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
public class AppRepoSCListener {

    @Autowired
    public AppRepoSCListener(ApplicationRepository scApplicationRepository) {
        EthFilter ethFilter = new EthFilter(LATEST, LATEST, scApplicationRepository.getContractAddress().substring(2))
                .addSingleTopic(EventEncoder.encode(ApplicationRepository.APPLICATIONSAVED_EVENT));
        scApplicationRepository.applicationSavedEventObservable(ethFilter).subscribe(this::handleEvent, this::handleError);
    }

    private void handleEvent(ApplicationRepository.ApplicationSavedEventResponse event) {
        log.info("[x] {}", event);
    }

    private void handleError(Throwable throwable) {
        log.error("[x] Error", throwable);
    }

}
