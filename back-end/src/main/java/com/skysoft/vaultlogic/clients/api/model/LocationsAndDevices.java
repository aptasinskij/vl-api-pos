package com.skysoft.vaultlogic.clients.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class LocationsAndDevices extends StatusCode {

    private List<Location> locations = new ArrayList<>();

}