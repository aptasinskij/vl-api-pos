package com.skysoft.vaultlogic.web.maya.clients.responce.device;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceInfoResponse extends BaseResponse {

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