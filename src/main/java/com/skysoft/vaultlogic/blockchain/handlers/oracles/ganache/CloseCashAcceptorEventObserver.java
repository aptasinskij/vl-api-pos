package com.skysoft.vaultlogic.blockchain.handlers.oracles.ganache;

import com.skysoft.vaultlogic.blockchain.contracts.CashInOracle;
import com.skysoft.vaultlogic.blockchain.contracts.CashInOracle.CloseCashAcceptorEventResponse;
import com.skysoft.vaultlogic.blockchain.handlers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.blockchain.handlers.api.EventObservable;
import com.skysoft.vaultlogic.web.service.CashInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import java.util.function.Function;

import static com.skysoft.vaultlogic.blockchain.contracts.CashInOracle.CLOSECASHACCEPTOR_EVENT;

@Slf4j
@Component
@Profile("ganache")
public class CloseCashAcceptorEventObserver extends AbstractContractEventObserver<CloseCashAcceptorEventResponse, CashInOracle> {

    private final CashInService cashInService;

    @Autowired
    public CloseCashAcceptorEventObserver(CashInOracle cashInOracle, CashInService cashInService) {
        super(cashInOracle);
        this.cashInService = cashInService;
    }

    @Override
    protected Event eventToFilterFor() {
        return CLOSECASHACCEPTOR_EVENT;
    }

    @Override
    protected EventObservable<CloseCashAcceptorEventResponse> getEventObservable() {
        return contract::closeCashAcceptorEventObservable;
    }

    @Override
    protected Function<CashInOracle, String> getAddress() {
        return contract -> contract.getContractAddress().substring(2);
    }

    @Override
    public void onNext(CloseCashAcceptorEventResponse event) {
        log.info("[x] CLOSE CASH ACCEPTOR: Channel: {}, XToken: {}", event.channelId, event.sessionId);
        cashInService.closeCashInChannel(event.channelId, event.sessionId);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error: CLOSE CASH ACCEPTOR:", throwable);
    }

}
