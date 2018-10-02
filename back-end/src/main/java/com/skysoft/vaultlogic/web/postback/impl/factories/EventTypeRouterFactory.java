package com.skysoft.vaultlogic.web.postback.impl.factories;

import com.skysoft.vaultlogic.web.postback.api.Event;
import com.skysoft.vaultlogic.web.postback.api.factory.AbstractHandlerFactory;
import com.skysoft.vaultlogic.web.postback.impl.factories.markers.EventTypeRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventTypeRouterFactory extends AbstractHandlerFactory<EventTypeRouter> {

    @Autowired
    public EventTypeRouterFactory(List<EventTypeRouter> handlersList) {
        super(handlersList);
    }

    @Override
    protected String getKey(Event<?> event) {
        return event.getEventType();
    }

}
