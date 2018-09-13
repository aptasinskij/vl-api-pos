package com.skysoft.vaultlogic.blockchain.handlers.api;

import org.web3j.contracts.SmartContractEvent;
import rx.Observer;

public interface SmartContractEventObserver<E extends SmartContractEvent> extends Observer<E> {

}
