package com.skysoft.vaultlogic.blockchain.handlers;

import com.skysoft.vaultlogic.blockchain.contracts.SessionOracle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.core.methods.request.EthFilter;

import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
public class CloseSessionEventHandler {

    @Autowired
    public CloseSessionEventHandler(SessionOracle sessionOracle) {
        sessionOracle.closeSessionEventObservable(buildFilter(sessionOracle))
                .subscribe(this::onNext, this::onError);
    }

    private EthFilter buildFilter(SessionOracle oracle) {
        return new EthFilter(LATEST, LATEST, oracle.getContractAddress().substring(2))
                .addSingleTopic(EventEncoder.encode(SessionOracle.CLOSESESSION_EVENT));
    }

    private void onNext(SessionOracle.CloseSessionEventResponse event) {
        log.info("[x] CLOSE SESSION EVENT: XToken: {}", event.xToken);
        log.info("[x] Here can be logic to send close application request to MAYA");
    }

    private void onError(Throwable throwable) {
        log.error("[x] Error filtering for open cash acceptor event", throwable);
    }

}
