package com.skysoft.vaultlogic.observers.oracles.ganache;

import com.skysoft.vaultlogic.contracts.CashInOracle;
import com.skysoft.vaultlogic.contracts.CashInOracle.OpenCashAcceptorEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import com.skysoft.vaultlogic.services.CashInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import static com.skysoft.vaultlogic.contracts.CashInOracle.OPENCASHACCEPTOR_EVENT;

@Slf4j
@Component
@Profile({"ganache", "local-quorum"})
public class OpenCashAcceptorEventHandler extends AbstractContractEventObserver<OpenCashAcceptorEventResponse, CashInOracle> {

    private final CashInService cashInService;

    @Autowired
    public OpenCashAcceptorEventHandler(CashInOracle cashInOracle, CashInService cashInService) {
        super(cashInOracle);
        this.cashInService = cashInService;
        subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return OPENCASHACCEPTOR_EVENT;
    }

    @Override
    protected EventObservable<OpenCashAcceptorEventResponse> getEventObservable() {
        return contract::openCashAcceptorEventObservable;
    }

    /*@Override
    protected Function<CashInOracle, String> getAddress() {
        return contract -> contract.getContractAddress().substring(2);
    }*/

    @Override
    public void onNext(OpenCashAcceptorEventResponse event) {
        log.info("[x] Open Cash ACCEPTOR: Channel: {}, Session: {}, Status: {}", event.channelId, event.sessionId, event.channelStatus);
        cashInService.createCashInChannel(event.channelId, event.sessionId, event.channelStatus);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering for open cash acceptor event", throwable);
    }

}
