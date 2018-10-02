package com.skysoft.vaultlogic.observers.oracles.quorum;

import com.skysoft.vaultlogic.contracts.SessionOracle;
import com.skysoft.vaultlogic.contracts.SessionOracle.CloseSessionEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import com.skysoft.vaultlogic.services.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import static com.skysoft.vaultlogic.contracts.SessionOracle.CLOSESESSION_EVENT;

@Slf4j
@Component
@Profile("cloud-quorum")
public class CloseSessionEventObserver extends AbstractContractEventObserver<CloseSessionEventResponse, SessionOracle> {

    private final SessionService sessionService;

    @Autowired
    public CloseSessionEventObserver(SessionOracle sessionOracle, SessionService sessionService) {
        super(sessionOracle);
        this.sessionService = sessionService;
        subscribe();
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
    public void onNext(CloseSessionEventResponse event) {
        log.info("[x] CLOSE SESSION EVENT: XToken: {}", event.sessionId);
        sessionService.closeSession(event.sessionId);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering for open cash acceptor event", throwable);
    }

}
