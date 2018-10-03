package com.skysoft.vaultlogic.postback.api.factory;

import com.skysoft.vaultlogic.postback.api.Event;
import com.skysoft.vaultlogic.postback.api.Handler;

public interface HandlerFactory<T extends Handler> {

    T getHandler(final Event<?> postBackEvent);

}
