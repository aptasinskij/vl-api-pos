package com.skysoft.vaultlogic.blockchain.handlers.cloud.storage.cashchannel;

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
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.tx.Contract;

import java.util.function.Function;

import static com.skysoft.vaultlogic.blockchain.contracts.CashInOracle.OPENCASHACCEPTOR_EVENT;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
@Profile("cloud-quorum")
public class OpenCashAcceptorEventObserver extends AbstractContractEventObserver<OpenCashAcceptorEventResponse, CashInOracle> {

    private final CashInService cashInService;

    @Autowired
    public OpenCashAcceptorEventObserver(CashInOracle cashInOracle, CashInService cashInService) {
        super(cashInOracle);
        this.cashInService = cashInService;
    }

    @Override
    protected Event getEvent() {
        return OPENCASHACCEPTOR_EVENT;
    }

    @Override
    protected EventObservable<OpenCashAcceptorEventResponse> getObservable() {
        return contract::openCashAcceptorEventObservable;
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
    public void onNext(OpenCashAcceptorEventResponse event) {
        log.info("[x] Open Cash ACCEPTOR: Channel: {}, Session: {}, Status: {}", event.channelId, event.sessionId, event.channelStatus);
        cashInService.createCashInChannel(event.channelId, event.sessionId, event.channelStatus);
    }

    @Override
    public void onCompleted() {
        log.info("[x] Cash acceptor open completed.");
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering for open cash acceptor event", throwable);
    }

}
