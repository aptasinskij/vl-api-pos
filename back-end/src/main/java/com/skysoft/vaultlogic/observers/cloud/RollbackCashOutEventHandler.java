package com.skysoft.vaultlogic.observers.cloud;

import com.skysoft.vaultlogic.contracts.CashOutOracle;
import com.skysoft.vaultlogic.contracts.CashOutOracle.RollbackCashOutEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import javax.annotation.PostConstruct;

import static com.skysoft.vaultlogic.contracts.CashOutOracle.ROLLBACKCASHOUT_EVENT;

@Slf4j
@Component
@Profile("cloud")
public class RollbackCashOutEventHandler extends AbstractContractEventObserver<RollbackCashOutEventResponse, CashOutOracle> {

    @Autowired
    public RollbackCashOutEventHandler(CashOutOracle cashOutOracle) {
        super(cashOutOracle);
    }

    @Override
    @PostConstruct
    protected void subscribe() {
        super.subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return ROLLBACKCASHOUT_EVENT;
    }

    @Override
    protected EventObservable<RollbackCashOutEventResponse> getEventObservable() {
        return contract::rollbackCashOutEventObservable;
    }

    @Override
    public void onNext(RollbackCashOutEventResponse event) {
        log.info("[x] ROLLBACK CASH OUT EVENT: XToken: {}", event._commandId);
        contract.successRollback(event._commandId).observable().single().subscribe(
                tx -> log.info("[x] confirmed success rollback cash out channel"),
                er -> log.error("[x] error confirming rollback cash out channel")
        );
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering for rollback cash out event", throwable);
    }

}
