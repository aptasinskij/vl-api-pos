package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Receipt {

    private String id;

    private String data;

    @JsonProperty("wkhtmltopdf_params")
    private String params;

}