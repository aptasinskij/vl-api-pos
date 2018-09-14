package com.skysoft.vaultlogic.blockchain.handlers.cloud.storage.cashchannel;

import com.skysoft.vaultlogic.blockchain.contracts.CashInOracle;
import com.skysoft.vaultlogic.blockchain.contracts.CashInOracle.CloseCashAcceptorEventResponse;
import com.skysoft.vaultlogic.blockchain.handlers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.blockchain.handlers.api.EventObservable;
import com.skysoft.vaultlogic.web.service.CashInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.tx.Contract;

import java.util.function.Function;

import static com.skysoft.vaultlogic.blockchain.contracts.CashInOracle.CLOSECASHACCEPTOR_EVENT;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
@Profile("cloud-quorum")
public class CloseCashAcceptorEventObserver extends AbstractContractEventObserver<CloseCashAcceptorEventResponse, CashInOracle> {

    private final CashInService cashInService;

    @Autowired
    public CloseCashAcceptorEventObserver(CashInOracle cashInOracle, CashInService cashInService) {
        super(cashInOracle);
        this.cashInService = cashInService;
    }

    @Override
    protected Event getEvent() {
        return CLOSECASHACCEPTOR_EVENT;
    }

    @Override
    protected EventObservable<CloseCashAcceptorEventResponse> getObservable() {
        return contract::closeCashAcceptorEventObservable;
    }

    @Override
    protected DefaultBlockParameterName getFromBlock() {
        return LATEST;
    }

    @Override
    protected DefaultBlockParameterName getToBlock() {
        return LATEST;
    }

    @Override
    protected Function<CashInOracle, String> getAddressFunction() {
        return Contract::getContractAddress;
    }

    @Override
    public void onNext(CloseCashAcceptorEventResponse event) {
        log.info("[x] CLOSE CASH ACCEPTOR: Channel: {}, XToken: {}", event.channelId, event.sessionId);
        cashInService.closeCashInChannel(event.channelId, event.sessionId);
    }

    @Override
    public void onCompleted() {
        log.info("[x] Close cash acceptor completed.");
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error: CLOSE CASH ACCEPTOR", throwable);
    }

}
