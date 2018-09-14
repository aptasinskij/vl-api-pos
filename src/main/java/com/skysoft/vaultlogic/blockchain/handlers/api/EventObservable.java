package com.skysoft.vaultlogic.blockchain.handlers.api;

import org.web3j.contracts.SmartContractEvent;
import org.web3j.protocol.core.methods.request.EthFilter;
import rx.Observable;

import java.util.function.Function;

public interface EventObservable<E extends SmartContractEvent> extends Function<EthFilter, Observable<E>> {
}
