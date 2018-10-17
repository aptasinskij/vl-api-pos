package com.skysoft.vaultlogic.observers.cloud;

import com.skysoft.vaultlogic.clients.api.KioskPrinter;
import com.skysoft.vaultlogic.clients.api.model.Receipt;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.session.projections.SessionXToken;
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

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.function.Consumer;

import static com.skysoft.vaultlogic.contracts.PrinterOracle.RECEIPTPRINT_EVENT;

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
        SessionXToken sessionXToken = sessionRepository.findSessionXTokenById(event._sessionId);
        kioskPrinter.printReceipt(sessionXToken.xToken, Receipt.of(event._receiptId, event._data, event._params))
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
