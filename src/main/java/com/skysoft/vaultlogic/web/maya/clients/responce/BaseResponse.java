package com.skysoft.vaultlogic.web.maya.clients.responce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    @JsonProperty("error_code")
    private String errorCode;
    private String status;

}