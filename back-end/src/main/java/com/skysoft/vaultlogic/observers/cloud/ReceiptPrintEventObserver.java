package com.skysoft.vaultlogic.observers.cloud;

import com.skysoft.vaultlogic.clients.api.KioskPrinter;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.contracts.PrinterOracle;
import com.skysoft.vaultlogic.contracts.PrinterOracle.ReceiptPrintEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.generated.Bytes32;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.skysoft.vaultlogic.clients.api.model.Receipt.of;
import static com.skysoft.vaultlogic.contracts.PrinterOracle.RECEIPTPRINT_EVENT;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;


@Slf4j
@Component
@Profile("cloud")
public class ReceiptPrintEventObserver extends AbstractContractEventObserver<ReceiptPrintEventResponse, PrinterOracle> {

    private final KioskPrinter kioskPrinter;
    private final SessionRepository sessionRepository;

    @Autowired
    public ReceiptPrintEventObserver(PrinterOracle printerOracle, KioskPrinter kioskPrinter, SessionRepository sessionRepository) {
        super(printerOracle);
        this.kioskPrinter = kioskPrinter;
        this.sessionRepository = sessionRepository;
    }

    @Override
    @PostConstruct
    protected void subscribe() {
        super.subscribe();
    }

    @Override
    protected Event eventToFilterFor() {
        return RECEIPTPRINT_EVENT;
    }

    @Override
    protected EventObservable<ReceiptPrintEventResponse> getEventObservable() {
        return contract::receiptPrintEventObservable;
    }

    @Override
    @Transactional(readOnly = true)
    public void onNext(ReceiptPrintEventResponse event) {
        log.info("[x] Receipt print: {}, {}", event._commandId, event._sessionId);
        String xToken = sessionRepository.findSessionXTokenById(event._sessionId).getxToken();
        List<String> names = event._paramNames.stream().map(Bytes32::getValue).map(String::new).collect(toList());
        List<String> values = event._paramValues.stream().map(Bytes32::getValue).map(String::new).collect(toList());
        Map<String, String> parameters = range(0, names.size()).boxed().collect(toMap(names::get, values::get));
        kioskPrinter.printReceipt(xToken, of(event._receiptId, event._data, parameters))
                .onSuccess(confirmSuccessReceiptPrint(event._commandId))
                .onFailure(confirmFailReceiptPrint(event._commandId));
    }

    private Consumer<StatusCode> confirmSuccessReceiptPrint(BigInteger commandId) {
        return statusCode -> contract.successPrint(commandId).observable().take(1).subscribe(
                tx -> log.info("[x] confirmed success receipt print"),
                error -> log.error("[x] error confirming success receipt print")
        );
    }

    private Consumer<Throwable> confirmFailReceiptPrint(BigInteger commandId) {
        return throwable -> contract.failPrint(commandId).observable().take(1).subscribe(
                tx -> log.info("[x] confirmed fail receipt print"),
                error -> log.error("[x] error confirming receipt print fail")
        );
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering Receipt print Event.", throwable);
    }

}
