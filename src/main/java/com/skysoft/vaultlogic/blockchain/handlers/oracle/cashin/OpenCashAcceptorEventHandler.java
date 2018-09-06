package com.skysoft.vaultlogic.blockchain.handlers.oracle.cashin;

import com.skysoft.vaultlogic.blockchain.contracts.CashInOracle;
import com.skysoft.vaultlogic.blockchain.contracts.CashInOracle.OpenCashAcceptorEventResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.core.methods.request.EthFilter;

import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
public class OpenCashAcceptorEventHandler {

    @Autowired
    public OpenCashAcceptorEventHandler(CashInOracle cashInOracle) {
        cashInOracle.openCashAcceptorEventObservable(buildFilter(cashInOracle))
                .subscribe(this::onNext, this::onError);
    }

    private EthFilter buildFilter(CashInOracle oracle) {
        return new EthFilter(LATEST, LATEST, oracle.getContractAddress().substring(2))
                .addSingleTopic(EventEncoder.encode(CashInOracle.OPENCASHACCEPTOR_EVENT));
    }

    private void onNext(OpenCashAcceptorEventResponse event) {
        log.info("[x] Open Cash ACCEPTOR: Channel: {}, Session: {}, XToken: {}", event.channelId, event.sessionId, event.xToken);
    }

    private void onError(Throwable throwable) {
        log.error("[x] Error filtering for open cash acceptor event", throwable);
    }

}
