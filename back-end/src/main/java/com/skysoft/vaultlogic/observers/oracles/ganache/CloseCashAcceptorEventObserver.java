package com.skysoft.vaultlogic.observers.oracles.ganache;

import com.skysoft.vaultlogic.common.domain.cashin.CashInChannel;
import com.skysoft.vaultlogic.common.domain.cashin.CashInRepository;
import com.skysoft.vaultlogic.contracts.CashInOracle;
import com.skysoft.vaultlogic.contracts.CashInOracle.CloseCashAcceptorEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.abi.datatypes.Event;

import static com.skysoft.vaultlogic.contracts.CashInOracle.CLOSECASHACCEPTOR_EVENT;

@Slf4j
@Component
@Profile({"ganache", "local-quorum"})
public class CloseCashAcceptorEventObserver extends AbstractContractEventObserver<CloseCashAcceptorEventResponse, CashInOracle> {

    private final CashInRepository cashInRepository;

    @Autowired
    public CloseCashAcceptorEventObserver(CashInOracle cashInOracle, CashInRepository cashInRepository) {
        super(cashInOracle);
        this.cashInRepository = cashInRepository;
        subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return CLOSECASHACCEPTOR_EVENT;
    }

    @Override
    protected EventObservable<CloseCashAcceptorEventResponse> getEventObservable() {
        return contract::closeCashAcceptorEventObservable;
    }

    /*@Override
    protected Function<CashInOracle, String> getAddress() {
        return contract -> contract.getContractAddress().substring(2);
    }
*/
    @Override
    @Transactional
    public void onNext(CloseCashAcceptorEventResponse event) {
        log.info("[x] CLOSE CASH-IN CHANNEL ID: {}", event.channelId);
        cashInRepository.findById(event.channelId).map(CashInChannel::markCloseRequested).ifPresent(cashInRepository::save);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error: CLOSE CASH ACCEPTOR:", throwable);
    }

}
