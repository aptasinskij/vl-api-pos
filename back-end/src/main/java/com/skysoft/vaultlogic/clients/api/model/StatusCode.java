package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(value = NON_NULL)
public class StatusCode {

    private String errorCode;
    private String status;

}