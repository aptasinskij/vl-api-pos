package com.skysoft.vaultlogic.blockchain.handlers.cloud;

import com.skysoft.vaultlogic.blockchain.contracts.SessionOracle;
import com.skysoft.vaultlogic.web.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.core.methods.request.EthFilter;

import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
@Profile("cloud-quorum")
public class CloseSessionEventHandler {

    private final SessionService sessionService;

    @Autowired
    public CloseSessionEventHandler(SessionOracle sessionOracle, SessionService sessionService) {
        this.sessionService = sessionService;
        sessionOracle.closeSessionEventObservable(buildFilter(sessionOracle))
                .subscribe(this::onNext, this::onError);
    }

    private EthFilter buildFilter(SessionOracle oracle) {
        return new EthFilter(LATEST, LATEST, oracle.getContractAddress())
                .addSingleTopic(EventEncoder.encode(SessionOracle.CLOSESESSION_EVENT));
    }

    private void onNext(SessionOracle.CloseSessionEventResponse event) {
        log.info("[x] CLOSE SESSION EVENT: XToken: {}", event.xToken);
        sessionService.closeSession(event.xToken);
    }

    private void onError(Throwable throwable) {
        log.error("[x] Error filtering for open cash acceptor event", throwable);
    }

}
