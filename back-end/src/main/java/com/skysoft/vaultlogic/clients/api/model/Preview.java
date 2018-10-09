package com.skysoft.vaultlogic.clients.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Preview extends StatusCode {

    private String port;
    private String url;
    private String href;

}