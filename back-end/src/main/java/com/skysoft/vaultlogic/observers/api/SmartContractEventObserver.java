package com.skysoft.vaultlogic.observers.api;

import org.web3j.contracts.SmartContractEvent;
import rx.Observer;

public interface SmartContractEventObserver<E extends SmartContractEvent> extends Observer<E> {

    @Override
    default void onCompleted() {
        // NO-OP
    }

    @Override
    default void onError(Throwable e) {
        // NO-OP
    }

    @Override
    default void onNext(E e) {
        System.out.println("default");
    }

}
