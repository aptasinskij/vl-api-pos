package com.skysoft.vaultlogic.observers.cloud;

import com.skysoft.vaultlogic.contracts.PrinterOracle;
import com.skysoft.vaultlogic.contracts.PrinterOracle.ReceiptCreateEventResponse;
import com.skysoft.vaultlogic.observers.api.AbstractContractEventObserver;
import com.skysoft.vaultlogic.observers.api.EventObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Event;

import static com.skysoft.vaultlogic.contracts.PrinterOracle.RECEIPTCREATE_EVENT;

@Slf4j
@Component
@Profile("cloud")
public class ReceiptCreateEventObserver extends AbstractContractEventObserver<ReceiptCreateEventResponse, PrinterOracle> {

    @Autowired
    public ReceiptCreateEventObserver(PrinterOracle printerOracle) {
        super(printerOracle);
        subscribe();
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
    public void onNext(ReceiptCreateEventResponse event) {
        log.info("[x] Receipt create: {}, {}", event._commandId, event._sessionId);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[x] Error filtering Receipt Create Event.", throwable);
    }

}
