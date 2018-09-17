package com.skysoft.vaultlogic.blockchain.handlers.oracles.ganache;

import com.skysoft.vaultlogic.blockchain.contracts.SessionOracle;
import com.skysoft.vaultlogic.blockchain.contracts.SessionOracle.CloseSessionEventResponse;
import com.skysoft.vaultlogic.blockchain.handlers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.blockchain.handlers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import java.util.function.Function;

import static com.skysoft.vaultlogic.blockchain.contracts.SessionOracle.CLOSESESSION_EVENT;

@Slf4j
@Component
@Profile("ganache")
public class CloseSessionEventHandler extends AbstractContractEventObserver<CloseSessionEventResponse, SessionOracle> {

    @Autowired
    public CloseSessionEventHandler(SessionOracle sessionOracle) {
        super(sessionOracle);
    }

    @Override
    protected Event eventToFilterFor() {
        return CLOSESESSION_EVENT;
    }

    @Override
    protected EventObservable<CloseSessionEventResponse> getEventObservable() {
        return contract::closeSessionEventObservable;
    }

    @Override
    protected Function<SessionOracle, String> getAddress() {
        return contract -> contract.getContractAddress().substring(2);
    }

    @Override
    public void onNext(CloseSessionEventResponse event) {
        log.info("[x] CLOSE SESSION EVENT: XToken: {}", event.sessionId);
        log.info("[x] Here can be logic to send close application request to MAYA");
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering for open cash acceptor event", throwable);
    }

}
