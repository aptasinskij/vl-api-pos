package com.skysoft.vaultlogic.blockchain.handlers.api;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.datatypes.Event;
import org.web3j.contracts.SmartContractEvent;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.tx.Contract;
import rx.Observable;

import java.util.function.Function;

public abstract class AbstractContractEventObserver<E extends SmartContractEvent, C extends Contract> implements SmartContractEventObserver<E> {

    protected final C contract;
    protected final EthFilter ethFilter;

    public AbstractContractEventObserver(C contract) {
        this.contract = contract;
        this.ethFilter = getEthFilter(contract);
        getObservable().apply(this.ethFilter).subscribe(this);
    }

    private EthFilter getEthFilter(C contract) {
        return new EthFilter(getFromBlock(), getToBlock(), contract.getContractAddress()).addSingleTopic(EventEncoder.encode(getEvent()));
    }

    protected abstract Event getEvent();

    protected abstract Function<EthFilter, Observable<E>> getObservable();

    protected abstract DefaultBlockParameterName getFromBlock();

    protected abstract DefaultBlockParameterName getToBlock();

}
