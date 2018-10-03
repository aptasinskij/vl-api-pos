package com.skysoft.vaultlogic.postback.api;

public interface EventResponse {

    static EventEmptyResponse empty() {
        return new EventEmptyResponse();
    }

}
