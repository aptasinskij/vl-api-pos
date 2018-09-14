package com.skysoft.vaultlogic.blockchain.handlers.cloud.storage.session;

import com.skysoft.vaultlogic.blockchain.contracts.SessionOracle;
import com.skysoft.vaultlogic.blockchain.contracts.SessionOracle.CloseSessionEventResponse;
import com.skysoft.vaultlogic.blockchain.handlers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.blockchain.handlers.api.EventObservable;
import com.skysoft.vaultlogic.web.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.tx.Contract;

import java.util.function.Function;

import static com.skysoft.vaultlogic.blockchain.contracts.SessionOracle.CLOSESESSION_EVENT;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

@Slf4j
@Component
@Profile("cloud-quorum")
public class CloseSessionEventObserver extends AbstractContractEventObserver<CloseSessionEventResponse, SessionOracle> {

    private final SessionService sessionService;

    @Autowired
    public CloseSessionEventObserver(SessionOracle sessionOracle, SessionService sessionService) {
        super(sessionOracle);
        this.sessionService = sessionService;
    }

    @Override
    protected Event getEvent() {
        return CLOSESESSION_EVENT;
    }

    @Override
    protected EventObservable<CloseSessionEventResponse> getObservable() {
        return contract::closeSessionEventObservable;
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
    protected Function<SessionOracle, String> getAddressFunction() {
        return Contract::getContractAddress;
    }

    @Override
    public void onNext(CloseSessionEventResponse event) {
        log.info("[x] CLOSE SESSION EVENT: XToken: {}", event.xToken);
        sessionService.closeSession(event.xToken);
    }

    @Override
    public void onCompleted() {
        log.info("[x] Session close completed.");
    }

    public void onError(Throwable throwable) {
        log.error("[x] Error filtering for open cash acceptor event", throwable);
    }

}
