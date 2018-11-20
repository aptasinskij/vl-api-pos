package com.skysoft.vaultlogic.observers.cloud;

import com.skysoft.vaultlogic.contracts.CashOutOracle;
import com.skysoft.vaultlogic.contracts.CashOutOracle.OpenCashOutEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import javax.annotation.PostConstruct;

import static com.skysoft.vaultlogic.contracts.CashOutOracle.OPENCASHOUT_EVENT;

@Slf4j
@Component
@Profile("cloud")
public class OpenCashOutEventHandler extends AbstractContractEventObserver<OpenCashOutEventResponse, CashOutOracle> {

    @Autowired
    public OpenCashOutEventHandler(CashOutOracle cashOutOracle) {
        super(cashOutOracle);
    }

    @Override
    @PostConstruct
    protected void subscribe() {
        super.subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return OPENCASHOUT_EVENT;
    }

    @Override
    protected EventObservable<OpenCashOutEventResponse> getEventObservable() {
        return contract::openCashOutEventObservable;
    }

    @Override
    public void onNext(OpenCashOutEventResponse event) {
        log.info("[x] OPEN CASH OUT EVENT: XToken: {}", event._commandId);
        contract.successOpen(event._commandId).observable().single().subscribe(
                tx -> log.info("[x] confirmed success open cash out channel"),
                er -> log.error("[x] error confirming open cash out channel")
        );
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering for open cash out event", throwable);
    }

}
