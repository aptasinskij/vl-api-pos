package com.skysoft.vaultlogic.observers.cloud;

import com.skysoft.vaultlogic.contracts.CashInOracle;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import com.skysoft.vaultlogic.services.CashInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import javax.annotation.PostConstruct;
import java.math.BigInteger;

import static com.skysoft.vaultlogic.contracts.CashInOracle.OPENCASHACCEPTOR_EVENT;

@Slf4j
@Component
@Profile("cloud")
public class OpenCashAcceptorEventObserver extends AbstractContractEventObserver<CashInOracle.OpenCashInEventResponse, CashInOracle> {

    private final CashInService cashInService;

    @Autowired
    public OpenCashAcceptorEventObserver(CashInOracle cashInOracle, CashInService cashInService) {
        super(cashInOracle);
        this.cashInService = cashInService;
    }

    @Override
    @PostConstruct
    protected void subscribe() {
        super.subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return OPENCASHACCEPTOR_EVENT;
    }

    @Override
    protected EventObservable<CashInOracle.OpenCashInEventResponse> getEventObservable() {
        return contract::openCashInEventObservable;
    }

    @Override
    public void onNext(CashInOracle.OpenCashInEventResponse event) {
        log.info("[x] Open Cash ACCEPTOR: Channel: {}, Session: {}, Max balance: {}", event._commandId, event._sessionId, event._maxBalance);
        cashInService.createCashInChannel(event._commandId, event._sessionId, event._maxBalance, BigInteger.ZERO);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering for open cash acceptor event", throwable);
    }

}
