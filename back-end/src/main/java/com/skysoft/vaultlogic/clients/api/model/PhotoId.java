package com.skysoft.vaultlogic.clients.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PhotoId extends StatusCode {

    private String id;

}