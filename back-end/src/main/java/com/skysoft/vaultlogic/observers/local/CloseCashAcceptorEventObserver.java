package com.skysoft.vaultlogic.observers.local;

import com.skysoft.vaultlogic.common.domain.cashin.CashInChannel;
import com.skysoft.vaultlogic.common.domain.cashin.CashInRepository;
import com.skysoft.vaultlogic.contracts.CashInOracle;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.abi.datatypes.Event;

import static com.skysoft.vaultlogic.contracts.CashInOracle.CLOSECASHIN_EVENT;

@Slf4j
@Component
@Profile("local")
public class CloseCashAcceptorEventObserver extends AbstractContractEventObserver<CashInOracle.CloseCashInEventResponse, CashInOracle> {

    private final CashInRepository cashInRepository;

    @Autowired
    public CloseCashAcceptorEventObserver(CashInOracle cashInOracle, CashInRepository cashInRepository) {
        super(cashInOracle);
        this.cashInRepository = cashInRepository;
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
    @Transactional
    public void onNext(CashInOracle.CloseCashInEventResponse event) {
        log.info("[x] CLOSE CASH-IN CHANNEL ID: {}", event._cashInId);
        cashInRepository.findById(event._cashInId).map(CashInChannel::markCloseRequested).ifPresent(cashInRepository::save);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error: CLOSE CASH ACCEPTOR:", throwable);
    }

}
