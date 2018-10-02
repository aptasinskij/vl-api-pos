package com.skysoft.vaultlogic.web.postback.api;

public interface Handler {

    String getName();

    EventResponse handle(final Event<?> event, String xToken);

}
