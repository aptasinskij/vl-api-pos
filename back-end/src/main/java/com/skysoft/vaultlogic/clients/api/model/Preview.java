package com.skysoft.vaultlogic.clients.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Preview extends StatusCode {

    private String port = "none";
    private String url = "none";
    private String href = "none";

}