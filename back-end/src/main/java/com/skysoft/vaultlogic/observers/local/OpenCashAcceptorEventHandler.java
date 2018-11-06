package com.skysoft.vaultlogic.observers.local;

import com.skysoft.vaultlogic.contracts.CashInOracle;
import com.skysoft.vaultlogic.contracts.CashInOracle.OpenCashInEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import com.skysoft.vaultlogic.services.CashInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import java.math.BigInteger;

import static com.skysoft.vaultlogic.contracts.CashInOracle.OPENCASHIN_EVENT;

@Slf4j
@Component
@Profile("local")
public class OpenCashAcceptorEventHandler extends AbstractContractEventObserver<OpenCashInEventResponse, CashInOracle> {

    private final CashInService cashInService;

    @Autowired
    public OpenCashAcceptorEventHandler(CashInOracle cashInOracle, CashInService cashInService) {
        super(cashInOracle);
        this.cashInService = cashInService;
        subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return OPENCASHIN_EVENT;
    }

    @Override
    protected EventObservable<OpenCashInEventResponse> getEventObservable() {
        return contract::openCashInEventObservable;
    }

    @Override
    public void onNext(OpenCashInEventResponse event) {
        log.info("[x] Open Cash ACCEPTOR: Channel: {}, Session: {}, MAX_BALANCE: {}", event._commandId, event._sessionId, event._maxBalance);
        cashInService.createCashInChannel(event._commandId, event._sessionId, event._maxBalance, BigInteger.ZERO);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering for open cash acceptor event", throwable);
    }

}
