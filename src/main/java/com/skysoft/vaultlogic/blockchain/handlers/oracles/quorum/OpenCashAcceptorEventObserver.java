package com.skysoft.vaultlogic.blockchain.handlers.oracles.quorum;

import com.skysoft.vaultlogic.blockchain.contracts.CashInOracle;
import com.skysoft.vaultlogic.blockchain.contracts.CashInOracle.OpenCashAcceptorEventResponse;
import com.skysoft.vaultlogic.blockchain.handlers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.blockchain.handlers.api.EventObservable;
import com.skysoft.vaultlogic.web.service.CashInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import static com.skysoft.vaultlogic.blockchain.contracts.CashInOracle.OPENCASHACCEPTOR_EVENT;

@Slf4j
@Component
@Profile("cloud-quorum")
public class OpenCashAcceptorEventObserver extends AbstractContractEventObserver<OpenCashAcceptorEventResponse, CashInOracle> {

    private final CashInService cashInService;

    @Autowired
    public OpenCashAcceptorEventObserver(CashInOracle cashInOracle, CashInService cashInService) {
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
