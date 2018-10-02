package com.skysoft.vaultlogic.observers.api;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.datatypes.Event;
import org.web3j.contracts.SmartContractEvent;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.tx.Contract;
import rx.Subscription;

import java.util.Objects;
import java.util.function.Function;

import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

public abstract class AbstractContractEventObserver<E extends SmartContractEvent, C extends Contract> implements SmartContractEventObserver<E> {

    protected final C contract;
    private final EthFilter ethFilter;
    private Subscription subscription;

    public AbstractContractEventObserver(C contract) {
        this.contract = contract;
        this.ethFilter = getEthFilter(contract);
    }

    private EthFilter getEthFilter(C contract) {
        return new EthFilter(getFromBlock(), getToBlock(), getAddress().apply(contract)).addSingleTopic(EventEncoder.encode(eventToFilterFor()));
    }

    protected abstract Event eventToFilterFor();

    protected abstract EventObservable<E> getEventObservable();

    protected DefaultBlockParameterName getFromBlock() {
        return LATEST;
    }

    protected DefaultBlockParameterName getToBlock() {
        return LATEST;
    }

    protected Function<C, String> getAddress() {
        return C::getContractAddress;
    }

    protected void subscribe() {
        if (Objects.nonNull(subscription)) this.subscription.unsubscribe();
        this.subscription = getEventObservable().apply(this.ethFilter).subscribe(this);
    }

}
