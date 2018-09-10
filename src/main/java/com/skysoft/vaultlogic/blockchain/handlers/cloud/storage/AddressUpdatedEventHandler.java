package com.skysoft.vaultlogic.blockchain.handlers.cloud.storage;

import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage;
import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.ApplicationAddressUpdatedEventResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.core.methods.request.EthFilter;

import static com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.APPLICATIONADDRESSUPDATED_EVENT;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
@Profile("cloud-quorum")
public class AddressUpdatedEventHandler {

    @Autowired
    public AddressUpdatedEventHandler(ApplicationStorage applicationStorage) {
        applicationStorage.applicationAddressUpdatedEventObservable(buildFilter(applicationStorage))
                .subscribe(this::onNext, this::onError);
    }

    private EthFilter buildFilter(ApplicationStorage applicationStorage) {
        return new EthFilter(LATEST, LATEST, applicationStorage.getContractAddress())
                .addSingleTopic(EventEncoder.encode(APPLICATIONADDRESSUPDATED_EVENT));
    }

    private void onNext(ApplicationAddressUpdatedEventResponse event) {
        log.info("[x] Application address updated : {}, {}", event.appId, event.appAddr);
    }

    private void onError(Throwable throwable) {
        log.error("[x] Error handler ApplicationSaved event", throwable);
    }


}
