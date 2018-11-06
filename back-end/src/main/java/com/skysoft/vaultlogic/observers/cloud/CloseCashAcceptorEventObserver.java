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

import static com.skysoft.vaultlogic.contracts.CashInOracle.CLOSECASHIN_EVENT;

@Slf4j
@Component
@Profile("cloud")
public class CloseCashAcceptorEventObserver extends AbstractContractEventObserver<CashInOracle.CloseCashInEventResponse, CashInOracle> {

    private final CashInService cashInService;

    @Autowired
    public CloseCashAcceptorEventObserver(CashInOracle cashInOracle, CashInService cashInService) {
        super(cashInOracle);
        this.cashInService = cashInService;
        subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return CLOSECASHIN_EVENT;
    }

    @Override
    protected EventObservable<CashInOracle.CloseCashInEventResponse> getEventObservable() {
        return contract::closeCashInEventObservable;
    }

    @Override
    public void onNext(CashInOracle.CloseCashInEventResponse event) {
        log.info("[x] CLOSE CASH ACCEPTOR: Channel: {}, XToken: {}", event._cashInId, event._sessionId);
        cashInService.closeCashInChannel(event._cashInId, event._sessionId);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error: CLOSE CASH ACCEPTOR", throwable);
    }

}
