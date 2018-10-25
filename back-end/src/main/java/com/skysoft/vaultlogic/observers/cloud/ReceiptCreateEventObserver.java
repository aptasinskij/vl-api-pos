package com.skysoft.vaultlogic.observers.cloud;

import com.skysoft.vaultlogic.clients.api.KioskPrinter;
import com.skysoft.vaultlogic.clients.api.model.ReceiptIdUrl;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.session.projections.SessionXToken;
import com.skysoft.vaultlogic.contracts.PrinterOracle;
import com.skysoft.vaultlogic.contracts.PrinterOracle.ReceiptCreateEventResponse;
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

import static com.skysoft.vaultlogic.contracts.PrinterOracle.RECEIPTCREATE_EVENT;

@Slf4j
@Component
@Profile("cloud")
public class ReceiptCreateEventObserver extends AbstractContractEventObserver<ReceiptCreateEventResponse, PrinterOracle> {

    private final KioskPrinter kioskPrinter;
    private final SessionRepository sessionRepository;

    @Autowired
    public ReceiptCreateEventObserver(PrinterOracle printerOracle, KioskPrinter kioskPrinter, SessionRepository sessionRepository) {
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
        return RECEIPTCREATE_EVENT;
    }

    @Override
    protected EventObservable<ReceiptCreateEventResponse> getEventObservable() {
        return contract::receiptCreateEventObservable;
    }

    @Override
    @Transactional(readOnly = true)
    public void onNext(ReceiptCreateEventResponse event) {
        log.info("[x] Receipt create: {}, {}", event._commandId, event._sessionId);
        SessionXToken sessionXToken = sessionRepository.findSessionXTokenById(event._sessionId);
        kioskPrinter.createReceipt(sessionXToken.getxToken())
                .onSuccess(confirmSuccessReceiptCreate(event._commandId))
                .onFailure(confirmFailReceiptCreate(event._commandId));
    }

    private Consumer<ReceiptIdUrl> confirmSuccessReceiptCreate(BigInteger commandId) {
        return receiptIdUrl -> contract.successCreate(commandId, receiptIdUrl.getId(), receiptIdUrl.getUrl()).observable().take(1).subscribe(
                tx -> log.info("[x] confirmed success receipt creation"),
                error -> log.error("[x] error confirming success receipt creation")
        );
    }

    private Consumer<Throwable> confirmFailReceiptCreate(BigInteger commandId) {
        return throwable -> contract.failCreate(commandId).observable().take(1).subscribe(
                tx -> log.info("[x] confirmed fail receipt creation"),
                error -> log.error("[x] error confirming receipt creation fail")
        );
    }


    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering Receipt Create Event.", throwable);
    }

}
