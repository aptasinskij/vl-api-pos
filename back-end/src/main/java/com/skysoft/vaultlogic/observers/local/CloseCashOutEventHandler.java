package com.skysoft.vaultlogic.observers.local;

import com.skysoft.vaultlogic.contracts.CashOutOracle;
import com.skysoft.vaultlogic.contracts.CashOutOracle.CloseCashOutEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import javax.annotation.PostConstruct;

import static com.skysoft.vaultlogic.contracts.CashOutOracle.CLOSECASHOUT_EVENT;

@Slf4j
@Component
@Profile("local")
public class CloseCashOutEventHandler extends AbstractContractEventObserver<CloseCashOutEventResponse, CashOutOracle> {

    @Autowired
    public CloseCashOutEventHandler(CashOutOracle cashOutOracle) {
        super(cashOutOracle);
    }

    @Override
    @PostConstruct
    protected void subscribe() {
        super.subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return CLOSECASHOUT_EVENT;
    }

    @Override
    protected EventObservable<CloseCashOutEventResponse> getEventObservable() {
        return contract::closeCashOutEventObservable;
    }

    @Override
    public void onNext(CloseCashOutEventResponse event) {
        log.info("[x] CLOSE CASH OUT EVENT: XToken: {}", event._commandId);
        contract.successClose(event._commandId).observable().single().subscribe(
                tx -> log.info("[x] confirmed success close cash out channel"),
                error -> log.error("[x] error confirming close cash out channel")
        );
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering for close cash out event", throwable);
    }

}
