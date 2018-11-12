package com.skysoft.vaultlogic.observers.local;

import com.skysoft.vaultlogic.common.domain.session.Session;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.contracts.SessionOracle;
import com.skysoft.vaultlogic.contracts.SessionOracle.CloseSessionEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.abi.datatypes.Event;

import static com.skysoft.vaultlogic.contracts.SessionOracle.CLOSESESSION_EVENT;

@Slf4j
@Component
@Profile("local")
public class CloseSessionEventHandler extends AbstractContractEventObserver<CloseSessionEventResponse, SessionOracle> {

    private final SessionRepository sessionRepository;

    @Autowired
    public CloseSessionEventHandler(SessionOracle sessionOracle, SessionRepository sessionRepository) {
        super(sessionOracle);
        this.sessionRepository = sessionRepository;
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
    @Transactional
    public void onNext(CloseSessionEventResponse event) {
        log.info("[x] CLOSE SESSION EVENT: XToken: {}", event.sessionId);
        sessionRepository.findById(event.sessionId).map(Session::markCloseRequested).ifPresent(sessionRepository::save);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering for close session event", throwable);
    }

}
