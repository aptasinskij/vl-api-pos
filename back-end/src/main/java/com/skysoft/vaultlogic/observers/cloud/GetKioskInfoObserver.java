package com.skysoft.vaultlogic.observers.cloud;

import com.skysoft.vaultlogic.clients.api.KioskCashDevices;
import com.skysoft.vaultlogic.clients.api.KioskDevicesClient;
import com.skysoft.vaultlogic.clients.api.model.Cassette;
import com.skysoft.vaultlogic.clients.api.model.DispensableAmount;
import com.skysoft.vaultlogic.common.domain.kiosk.Kiosk;
import com.skysoft.vaultlogic.common.domain.kiosk.mapper.KioskMapper;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.session.projections.SessionXToken;
import com.skysoft.vaultlogic.contracts.KioskOracle;
import com.skysoft.vaultlogic.contracts.KioskOracle.GetKioskInfoEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.abi.datatypes.Event;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.skysoft.vaultlogic.clients.api.model.DispensableAmount.max;
import static com.skysoft.vaultlogic.contracts.KioskOracle.GETKIOSKINFO_EVENT;
import static io.vavr.API.Try;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Component
@Profile("cloud")
public class GetKioskInfoObserver extends AbstractContractEventObserver<GetKioskInfoEventResponse, KioskOracle> {

    private final SessionRepository sessionRepository;
    private final KioskDevicesClient kioskDevicesClient;
    private final KioskMapper kioskMapper;
    private final KioskCashDevices cashDevices;

    @Autowired
    public GetKioskInfoObserver(KioskOracle contract,
                                SessionRepository sessionRepository,
                                KioskDevicesClient kioskDevicesClient,
                                KioskMapper kioskMapper, KioskCashDevices cashDevices) {
        super(contract);
        this.sessionRepository = sessionRepository;
        this.kioskDevicesClient = kioskDevicesClient;
        this.kioskMapper = kioskMapper;
        this.cashDevices = cashDevices;
    }

    @Override
    @PostConstruct
    protected void subscribe() {
        super.subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return GETKIOSKINFO_EVENT;
    }

    @Override
    protected EventObservable<GetKioskInfoEventResponse> getEventObservable() {
        return contract::getKioskInfoEventObservable;
    }

    @Override
    @Transactional(readOnly = true)
    public void onNext(GetKioskInfoEventResponse event) {
        log.info("[x] GetKioskInfo request: ID: {}, SessionID: {}", event._commandId, event._sessionId);
        Try(() -> sessionRepository.findSessionXTokenById(event._sessionId))
                .map(SessionXToken::getxToken)
                .flatMap(xToken -> kioskDevicesClient.getKioskInfo(xToken)
                        .map(kioskMapper::fromKioskDevice)
                        .map(kiosk -> Pair.of(xToken, kiosk)))
                .flatMap(pair -> cashDevices.getDispensableAmount(pair.getFirst(), max())
                        .map(DispensableAmount::getCassettes)
                        .map(cassettes -> Pair.of(pair.getSecond(), cassettes)))
                .onSuccess(confirmSuccess(event))
                .onFailure(confirmFail(event));
    }

    private Consumer<Pair<Kiosk, List<Cassette>>> confirmSuccess(GetKioskInfoEventResponse event) {
        return kioskCassettes -> {
            List<Cassette> cassettes = kioskCassettes.getSecond();
            Kiosk kiosk = kioskCassettes.getFirst();
            Map<BigInteger, BigInteger> denominations = cassettes.stream().filter(Cassette::isCash)
                    .map(Cassette::denominationToCountPair)
                    .collect(toMap(Pair::getFirst, Pair::getSecond));
            contract.successGetKioskInfo(
                    event._commandId,
                    kiosk.getShortId(),
                    kiosk.getAddress(),
                    kiosk.getName(),
                    kiosk.getTimezone(),
                    new ArrayList<>(denominations.keySet()),
                    new ArrayList<>(denominations.values())
            ).observable().single().subscribe(
                    tx -> log.info("[x] Confirmed: {}", tx.getTransactionHash()),
                    er -> log.error("[x] Error: {}", er.getMessage())
            );
        };
    }

    private Consumer<Throwable> confirmFail(GetKioskInfoEventResponse event) {
        return throwable -> contract.failGetKioskInfo(event._commandId).observable().single().subscribe(
                tx -> log.info("[x] Confirmed fail: {}", tx.getTransactionHash()),
                er -> log.info("[x] Error confirm fail: {}", er.getMessage())
        );
    }

    @Override
    public void onError(Throwable e) {
        log.error("[x] Error to filter for GetKioskInfo event: {}", e.getMessage());
    }

}
