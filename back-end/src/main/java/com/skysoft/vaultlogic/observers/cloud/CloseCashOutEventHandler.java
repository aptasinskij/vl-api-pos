package com.skysoft.vaultlogic.observers.cloud;

import com.skysoft.vaultlogic.clients.api.KioskCashDevices;
import com.skysoft.vaultlogic.clients.api.model.Cassette;
import com.skysoft.vaultlogic.clients.api.model.DispenseCash;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.session.projections.SessionXToken;
import com.skysoft.vaultlogic.contracts.CashOutOracle;
import com.skysoft.vaultlogic.contracts.CashOutOracle.CloseCashOutEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.generated.Uint256;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Consumer;

import static com.skysoft.vaultlogic.contracts.CashOutOracle.CLOSECASHOUT_EVENT;
import static java.util.stream.Collectors.toList;

@Slf4j
@Component
@Profile("cloud")
public class CloseCashOutEventHandler extends AbstractContractEventObserver<CloseCashOutEventResponse, CashOutOracle> {

    private final SessionRepository sessionRepository;
    private final KioskCashDevices kioskCashDevices;

    @Autowired
    public CloseCashOutEventHandler(CashOutOracle cashOutOracle, SessionRepository sessionRepository, KioskCashDevices kioskCashDevices) {
        super(cashOutOracle);
        this.sessionRepository = sessionRepository;
        this.kioskCashDevices = kioskCashDevices;
    }

    @Override
    @PostConstruct
    protected void subscribe() {
        super.subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return CLOSECASHOUT_EVENT;
    }

    @Override
    protected EventObservable<CloseCashOutEventResponse> getEventObservable() {
        return contract::closeCashOutEventObservable;
    }

    @Override
    public void onNext(CloseCashOutEventResponse event) {
        log.info("[x] CLOSE CASH OUT EVENT: XToken: {}", event._commandId);
        SessionXToken sessionXToken = sessionRepository.findSessionXTokenById(event._sessionId);
        List<Cassette> cassettes = Cassette.fromBillsForDispense(event._bills.stream().map(Uint256::getValue).collect(toList()));
        DispenseCash dispenseCash = DispenseCash.from(event._toWithdraw, cassettes);
        kioskCashDevices.dispenseCash(sessionXToken.getxToken(), dispenseCash)
                .onSuccess(confirmSuccessCloseCashOut(event._commandId))
                .onFailure(confirmFailCloseCashOut(event._commandId));
    }

    private Consumer<StatusCode> confirmSuccessCloseCashOut(BigInteger commandId) {
        return statusCode -> contract.successClose(commandId).observable().single().subscribe(
                tx -> log.info("[x] confirmed success close cash out channel"),
                error -> log.error("[x] error confirming close cash out channel")
        );
    }

    private Consumer<Throwable> confirmFailCloseCashOut(BigInteger commandId) {
        return throwable -> contract.failClose(commandId).observable().single().subscribe(
                tx -> log.info("[x] confirmed fail close cash out channel"),
                error -> log.error("[x] error confirming fail get dispensable amount")
        );
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering for close cash out event", throwable);
    }

}
