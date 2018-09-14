package com.skysoft.vaultlogic.blockchain.handlers.local.oracle;

import com.skysoft.vaultlogic.blockchain.contracts.SessionOracle;
import com.skysoft.vaultlogic.blockchain.contracts.SessionOracle.CloseSessionEventResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.core.methods.request.EthFilter;

import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
@Profile("ganache")
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

    private void onNext(CloseSessionEventResponse event) {
        log.info("[x] CLOSE SESSION EVENT: XToken: {}", event.sessionId);
        log.info("[x] Here can be logic to send close application request to MAYA");
    }

    private void onError(Throwable throwable) {
        log.error("[x] Error filtering for open cash acceptor event", throwable);
    }

}
