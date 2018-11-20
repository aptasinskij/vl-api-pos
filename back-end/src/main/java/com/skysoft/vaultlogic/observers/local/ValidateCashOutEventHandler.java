package com.skysoft.vaultlogic.observers.local;

import com.skysoft.vaultlogic.contracts.CashOutOracle;
import com.skysoft.vaultlogic.contracts.CashOutOracle.ValidateCashOutEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import javax.annotation.PostConstruct;

import static com.skysoft.vaultlogic.contracts.CashOutOracle.VALIDATECASHOUT_EVENT;

@Slf4j
@Component
@Profile("local")
public class ValidateCashOutEventHandler extends AbstractContractEventObserver<ValidateCashOutEventResponse, CashOutOracle> {

    @Autowired
    public ValidateCashOutEventHandler(CashOutOracle cashOutOracle) {
        super(cashOutOracle);
    }

    @Override
    @PostConstruct
    protected void subscribe() {
        super.subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return VALIDATECASHOUT_EVENT;
    }

    @Override
    protected EventObservable<ValidateCashOutEventResponse> getEventObservable() {
        return contract::validateCashOutEventObservable;
    }

    @Override
    public void onNext(ValidateCashOutEventResponse event) {
        log.info("[x] VALIDATE CASH OUT EVENT: XToken: {}", event._commandId);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering for validate cash out event", throwable);
    }

}
