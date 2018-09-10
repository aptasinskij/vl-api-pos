package com.skysoft.vaultlogic.blockchain.handlers.local;

import com.skysoft.vaultlogic.blockchain.contracts.CashInOracle;
import com.skysoft.vaultlogic.blockchain.contracts.CashInOracle.CloseCashAcceptorEventResponse;
import com.skysoft.vaultlogic.web.service.CashInService;
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
public class CloseCashAcceptorEventHandler {

    private final CashInOracle cashInOracle;
    private final CashInService cashInService;

    @Autowired
    public CloseCashAcceptorEventHandler(CashInOracle cashInOracle, CashInService cashInService) {
        this.cashInOracle = cashInOracle;
        this.cashInService = cashInService;
        cashInOracle.closeCashAcceptorEventObservable(buildFilter(cashInOracle))
                .subscribe(this::onNext, this::onError);
    }

    private EthFilter buildFilter(CashInOracle oracle) {
        return new EthFilter(LATEST, LATEST, oracle.getContractAddress().substring(2))
                .addSingleTopic(EventEncoder.encode(CashInOracle.CLOSECASHACCEPTOR_EVENT));
    }

    private void onNext(CloseCashAcceptorEventResponse event) {
        log.info("[x] CLOSE CASH ACCEPTOR: Channel: {}, XToken: {}", event.channelId, event.xToken);
        cashInService.closeCashInChannel(event.channelId, event.xToken);
    }

    private void onError(Throwable throwable) {
        log.error("[x] Error: CLOSE CASH ACCEPTOR", throwable);
    }

}
