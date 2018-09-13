package com.skysoft.vaultlogic.blockchain.handlers.api;

import com.skysoft.vaultlogic.blockchain.contracts.ApplicationStorage.ApplicationStorageEvent;

public interface ApplicationStorageEventObserver<E extends ApplicationStorageEvent> extends SmartContractEventObserver<E> {
}
