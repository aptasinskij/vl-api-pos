package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Receipt {

    private String id;

    private String data;

    @JsonProperty("wkhtmltopdf_params")
    private Map<String, String> params = new HashMap<>();

    public Receipt() {
    }

    public Receipt(String id, String data, String params) {
        this.id = id;
        this.data = data;
        this.params.put("orientation", params);
    }

    public static Receipt of(String id, String data, String params) {
        return new Receipt(id, data, params);
    }

}