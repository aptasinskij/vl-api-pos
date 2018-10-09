package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Location extends StatusCode {

    @JsonProperty("location_id")
    private String locationId;

    @JsonProperty("business_name")
    private String businessName;

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String zip;

    private String country;

    private String longitude;

    private String latitude;

    private String hidden;

    private String description;

    private String timezone;

    private List<KioskDevice> devices = new ArrayList<>();

}
