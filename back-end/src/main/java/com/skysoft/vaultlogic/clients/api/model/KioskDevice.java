package com.skysoft.vaultlogic.clients.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class KioskDevice extends StatusCode {

    private String deviceId;
    private String deviceCurrency;
    private String info;
    private String shortId;
    private String isTest;
    private String locationId;
    private String businessName;
    private String address1;
    private String address2;
    private String formattedAddress;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String longitude;
    private String latitude;
    private String hidden;
    private String description;
    private String timezone;

}