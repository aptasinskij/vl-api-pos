package com.skysoft.vaultlogic.postback.api;

public class EventEmptyResponse implements EventResponse {

    private String status = "OK";

    public String getStatus() {
        return status;
    }
}
