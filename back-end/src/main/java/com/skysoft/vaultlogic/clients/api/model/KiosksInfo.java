package com.skysoft.vaultlogic.clients.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class KiosksInfo extends StatusCode {

    private List<KioskDevice> devices;

}