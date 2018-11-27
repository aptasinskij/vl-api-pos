package com.skysoft.vaultlogic.observers.cloud;

import com.skysoft.vaultlogic.clients.api.KioskCashDevices;
import com.skysoft.vaultlogic.clients.api.model.Cassette;
import com.skysoft.vaultlogic.clients.api.model.DispensableAmount;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.session.projections.SessionXToken;
import com.skysoft.vaultlogic.contracts.CashOutOracle;
import com.skysoft.vaultlogic.contracts.CashOutOracle.ValidateCashOutEventResponse;
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

import static com.skysoft.vaultlogic.clients.api.model.DispensableAmount.greaterOrEqualTo;
import static com.skysoft.vaultlogic.contracts.CashOutOracle.VALIDATECASHOUT_EVENT;
import static java.util.stream.Collectors.toList;

@Slf4j
@Component
@Profile("cloud")
public class ValidateCashOutEventHandler extends AbstractContractEventObserver<ValidateCashOutEventResponse, CashOutOracle> {

    private final KioskCashDevices kioskCashDevices;
    private final SessionRepository sessionRepository;

    @Autowired
    public ValidateCashOutEventHandler(CashOutOracle cashOutOracle, KioskCashDevices kioskCashDevices, SessionRepository sessionRepository) {
        super(cashOutOracle);
        this.kioskCashDevices = kioskCashDevices;
        this.sessionRepository = sessionRepository;
    }

    @Override
    @PostConstruct
    protected void subscribe() {
        super.subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return VALIDATECASHOUT_EVENT;
    }

    @Override
    protected EventObservable<ValidateCashOutEventResponse> getEventObservable() {
        return contract::validateCashOutEventObservable;
    }

    @Override
    public void onNext(ValidateCashOutEventResponse event) {
        log.info("[x] VALIDATE CASH OUT EVENT: XToken: {}", event._commandId);
        SessionXToken sessionXToken = sessionRepository.findSessionXTokenById(event._sessionId);
        List<Cassette> cassettes = Cassette.fromBillsForDispense(event._bills.stream().map(Uint256::getValue).collect(toList()));
        DispensableAmount dispensableAmount = DispensableAmount.from(event._toWithdraw, cassettes);
        kioskCashDevices.getDispensableAmount(sessionXToken.getxToken(), dispensableAmount)
                .filter(greaterOrEqualTo(event._toWithdraw))
                .onSuccess(confirmSuccessValidate(event._commandId))
                .onFailure(confirmFailValidate(event._commandId));
    }

    private Consumer<StatusCode> confirmSuccessValidate(BigInteger commandId) {
        return dispensableAmount -> contract.successValidate(commandId).observable().single().subscribe(
                tx -> log.info("[x] confirmed success validate dispensable amount"),
                error -> log.error("[x] error confirming validate dispensable amount")
        );
    }

    private Consumer<Throwable> confirmFailValidate(BigInteger commandId) {
        return throwable -> contract.failValidate(commandId).observable().single().subscribe(
                tx -> log.info("[x] confirmed fail validate dispensable amount"),
                error -> log.error("[x] error confirming fail validate dispensable amount")
        );
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering for validate cash out event", throwable);
    }

}
