package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class KioskDevice extends StatusCode {

    @JsonProperty("device_id")
    private String deviceId;

    @JsonProperty("device_currency")
    private String deviceCurrency;

    private String info;

    @JsonProperty("short_id")
    private String shortId;

    @JsonProperty("is_test")
    private String isTest;

    @JsonProperty("location_id")
    private String locationId;

    @JsonProperty("business_name")
    private String businessName;

    private String address1;
    private String address2;

    @JsonProperty("formatted_address")
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