package com.skysoft.vaultlogic.web.postback.api;

public interface EventResponse {

    static EventEmptyResponse empty() {
        return new EventEmptyResponse();
    }

}
