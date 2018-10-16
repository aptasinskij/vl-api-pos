package com.skysoft.vaultlogic.observers.cloud;

import com.skysoft.vaultlogic.contracts.PrinterOracle;
import com.skysoft.vaultlogic.contracts.PrinterOracle.ReceiptPrintEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import static com.skysoft.vaultlogic.contracts.PrinterOracle.RECEIPTPRINT_EVENT;

@Slf4j
@Component
@Profile("cloud")
public class ReceiptPrintEventObserver extends AbstractContractEventObserver<ReceiptPrintEventResponse, PrinterOracle> {

    @Autowired
    public ReceiptPrintEventObserver(PrinterOracle printerOracle) {
        super(printerOracle);
        subscribe();
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
    public void onNext(ReceiptPrintEventResponse event) {
        log.info("[x] Receipt print: {}, {}", event._commandId, event._sessionId);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering Receipt print Event.", throwable);
    }

}
