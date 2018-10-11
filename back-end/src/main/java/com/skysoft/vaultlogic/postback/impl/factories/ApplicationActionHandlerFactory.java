package com.skysoft.vaultlogic.postback.impl.factories;

import com.skysoft.vaultlogic.postback.api.Event;
import com.skysoft.vaultlogic.postback.api.factory.AbstractHandlerFactory;
import com.skysoft.vaultlogic.postback.impl.factories.markers.ApplicationActionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("cloud")
public class ApplicationActionHandlerFactory extends AbstractHandlerFactory<ApplicationActionHandler> {

    @Autowired
    public ApplicationActionHandlerFactory(List<ApplicationActionHandler> handlersList) {
        super(handlersList);
    }

    @Override
    protected String getKey(Event<?> event) {
        return event.getActionType();
    }

}
