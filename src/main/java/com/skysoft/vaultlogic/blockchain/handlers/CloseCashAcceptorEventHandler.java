package com.skysoft.vaultlogic.blockchain.handlers;

import com.skysoft.vaultlogic.blockchain.contracts.CashInOracle;
import com.skysoft.vaultlogic.blockchain.contracts.CashInOracle.CloseCashAcceptorEventResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.core.methods.request.EthFilter;

import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
public class CloseCashAcceptorEventHandler {

    private final CashInOracle cashInOracle;

    @Autowired
    public CloseCashAcceptorEventHandler(CashInOracle cashInOracle) {
        this.cashInOracle = cashInOracle;
        cashInOracle.closeCashAcceptorEventObservable(buildFilter(cashInOracle))
                .subscribe(this::onNext, this::onError);
    }

    private EthFilter buildFilter(CashInOracle oracle) {
        return new EthFilter(LATEST, LATEST, oracle.getContractAddress().substring(2))
                .addSingleTopic(EventEncoder.encode(CashInOracle.CLOSECASHACCEPTOR_EVENT));
    }

    private void onNext(CloseCashAcceptorEventResponse event) {
        log.info("[x] CLOSE CASH ACCEPTOR: Channel: {}, XToken: {}", event.channelId, event.xToken);
        cashInOracle.confirmClose(event.channelId).sendAsync()
                .thenAccept(tx -> log.info("[x] Success close. TX: {}", tx.getTransactionHash()))
                .exceptionally(th -> {
                    log.error("[x] Fail to confirm close", th);
                    return null;
                });
    }

    private void onError(Throwable throwable) {
        log.error("[x] Error: CLOSE CASH ACCEPTOR", throwable);
    }

}
