package com.skysoft.vaultlogic.blockchain.handlers.api;

import org.web3j.contracts.SmartContractEvent;
import org.web3j.protocol.core.methods.request.EthFilter;
import rx.Observable;


@FunctionalInterface
public interface EventObservable<E extends SmartContractEvent> {

    Observable<E> apply(EthFilter ethFilter);

}
