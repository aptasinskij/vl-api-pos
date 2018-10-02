package com.skysoft.vaultlogic.web.maya.clients.responce.generalInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllLocationsAndDevicesResponse extends BaseResponse {

    public List<Location> locations;

    @Data
    private class Location {

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
        private List<Device> devices;

        private class Device {

            @JsonProperty("device_id")
            private String deviceId;

            @JsonProperty("deviceCurrency")
            private String device_currency;

            private String info;

            @JsonProperty("short_id")
            private String shortId;

            @JsonProperty("is_test")
            private String isTest;

        }

    }

}