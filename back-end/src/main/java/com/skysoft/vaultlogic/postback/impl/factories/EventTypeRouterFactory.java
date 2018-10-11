package com.skysoft.vaultlogic.postback.impl.factories;

import com.skysoft.vaultlogic.postback.api.Event;
import com.skysoft.vaultlogic.postback.api.factory.AbstractHandlerFactory;
import com.skysoft.vaultlogic.postback.impl.factories.markers.EventTypeRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("cloud")
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
