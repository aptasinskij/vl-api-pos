package com.skysoft.vaultlogic.postback.api;

import com.skysoft.vaultlogic.postback.api.factory.HandlerFactory;

/**
 * Abstract implementation of @see {@link EventRouter}
 * Base class for all concrete routers that handles events based on event, device and action type
 *
 * @param <T> - type of handler that can be produced by inner factory, @see {@link Handler}
 */
public abstract class AbstractEventRouter<T extends Handler> implements EventRouter {

    private final HandlerFactory<T> handlerFactory;

    protected AbstractEventRouter(HandlerFactory<T> handlerFactory) {
        this.handlerFactory = handlerFactory;
    }

    @Override
    public EventResponse handle(Event<?> event, String xToken) {
        return handlerFactory.getHandler(event).handle(event, xToken);
    }

}
