package com.skysoft.vaultlogic.observers.cloud;

import com.skysoft.vaultlogic.clients.api.KioskCashDevices;
import com.skysoft.vaultlogic.clients.api.KioskDevicesClient;
import com.skysoft.vaultlogic.clients.api.model.Cassette;
import com.skysoft.vaultlogic.clients.api.model.DispensableAmount;
import com.skysoft.vaultlogic.common.domain.kiosk.Kiosk;
import com.skysoft.vaultlogic.common.domain.kiosk.mapper.KioskMapper;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.contracts.KioskOracle;
import com.skysoft.vaultlogic.contracts.KioskOracle.GetKioskInfoEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import rx.functions.Action1;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.skysoft.vaultlogic.clients.api.model.DispensableAmount.max;
import static com.skysoft.vaultlogic.contracts.KioskOracle.GETKIOSKINFO_EVENT;
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
        String xToken = sessionRepository.findSessionXTokenById(event._sessionId).getxToken();
        kioskDevicesClient.getKioskInfo(xToken)
                .map(kioskMapper::fromKioskDevice)
                .flatMap(tryGetDispensableInfo(xToken))
                .onFailure(confirmFailGetKioskInfo(event))
                .onSuccess(confirmSuccessGetKioskInfo(event));
    }

    private Consumer<Throwable> confirmFailGetKioskInfo(GetKioskInfoEventResponse event) {
        return throwable -> contract.failGetKioskInfo(event._commandId).observable().single().subscribe(
                transaction -> log.info("[x] confirmed fail get kiosk info"),
                error -> log.error("[x] failed to confirm fail get kiosk info")
        );
    }

    private Function<Kiosk, Try<? extends Pair<Kiosk, List<Cassette>>>> tryGetDispensableInfo(String xToken) {
        return kiosk -> cashDevices.getDispensableAmount(xToken, max())
                .map(DispensableAmount::getCassettes)
                .map(cassettes -> Pair.of(kiosk, cassettes));
    }

    private Consumer<Pair<Kiosk, List<Cassette>>> confirmSuccessGetKioskInfo(GetKioskInfoEventResponse event) {
        return pair -> {
            Pair<List<BigInteger>, List<BigInteger>> denominationsAmounts = toDenominationAmountPair(pair.getSecond());
            contract.successGetKioskInfo(
                    event._commandId,
                    pair.getFirst().getShortId(),
                    pair.getFirst().getAddress(),
                    pair.getFirst().getName(),
                    pair.getFirst().getTimezone(),
                    denominationsAmounts.getFirst(),
                    denominationsAmounts.getSecond()
            ).observable().single().subscribe(logSuccessConfirmation(), logFailConfirmation());
        };
    }

    private Pair<List<BigInteger>, List<BigInteger>> toDenominationAmountPair(Collection<Cassette> cassettes) {
        Map<BigInteger, BigInteger> denominationAmount = cassettes.stream().collect(toMap(Cassette::getDenomination, Cassette::getAmount));
        return Pair.of(mapToList(denominationAmount, Map.Entry::getKey), mapToList(denominationAmount, Map.Entry::getValue));
    }

    private Action1<TransactionReceipt> logSuccessConfirmation() {
        return tx -> log.info("[x] confirmed success get kiosk info: {}", tx.getTransactionHash());
    }

    private Action1<Throwable> logFailConfirmation() {
        return error -> log.error("[x] failed confirm get kiosk info fail: {}", error.getMessage());
    }

    private <T, R> List<R> mapToList(Map<? extends T, ? extends R> denominations, Function<Map.Entry<? extends T, ? extends R>, ? extends R> mapper) {
        return denominations.entrySet().stream().map(mapper).collect(Collectors.toList());
    }

}
