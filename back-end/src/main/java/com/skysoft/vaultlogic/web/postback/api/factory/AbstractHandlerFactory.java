package com.skysoft.vaultlogic.web.postback.api.factory;

import com.skysoft.vaultlogic.web.postback.api.Event;
import com.skysoft.vaultlogic.web.postback.api.Handler;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Base class for all concrete event routers and handlers factories
 * implementation of @see {@link HandlerFactory}
 * @param <T> - type of handler or router that factory produces, @see {@link Handler}
 * */
public abstract class AbstractHandlerFactory<T extends Handler> implements HandlerFactory<T> {

    private final Map<String, T> handlersMap;

    public AbstractHandlerFactory(List<T> handlersList) {
        this.handlersMap = handlersList.stream().collect(Collectors.toMap(T::getName, Function.identity()));
    }

    @Override
    public T getHandler(final Event<?> event) {
        return handlersMap.get(getKey(event));
    }

    protected abstract String getKey(final Event<?> event);

}
