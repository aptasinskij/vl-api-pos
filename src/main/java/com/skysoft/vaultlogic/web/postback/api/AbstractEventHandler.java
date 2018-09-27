package com.skysoft.vaultlogic.web.postback.api;


import com.skysoft.vaultlogic.web.postback.mapper.DataMapper;

/**
 * Abstract implementation of @see {@link EventHandler}
 * Base class for all concrete event handlers
 *
 * @param <T> - type of event data, @see {@link EventData}
 * @param <R> - type of handler response, @see {@link EventResponse}
 */
public abstract class AbstractEventHandler<T extends EventData, R extends EventResponse> implements EventHandler {

    private final Class<T> eventDataType;
    private final DataMapper dataMapper;

    protected AbstractEventHandler(Class<T> eventDataType, DataMapper dataMapper) {
        this.eventDataType = eventDataType;
        this.dataMapper = dataMapper;
    }

    @Override
    public EventResponse handle(Event<?> event, String xToken) {
        T eventData = dataMapper.convertValue(event.getData(), eventDataType);
        return handleEvent(eventData, xToken);
    }

    protected abstract R handleEvent(T eventData, String xToken);

}
