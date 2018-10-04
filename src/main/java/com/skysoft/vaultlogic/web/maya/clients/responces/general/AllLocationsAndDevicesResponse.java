package com.skysoft.vaultlogic.web.maya.clients.responces.general;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.web.maya.clients.responces.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class AllLocationsAndDevicesResponse extends BaseResponse {

    public List<Location> locations;

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

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

        public String getLocationId() {
            return locationId;
        }

        public void setLocationId(String locationId) {
            this.locationId = locationId;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getHidden() {
            return hidden;
        }

        public void setHidden(String hidden) {
            this.hidden = hidden;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public List<Device> getDevices() {
            return devices;
        }

        public void setDevices(List<Device> devices) {
            this.devices = devices;
        }

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

            public String getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
            }

            public String getDevice_currency() {
                return device_currency;
            }

            public void setDevice_currency(String device_currency) {
                this.device_currency = device_currency;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getShortId() {
                return shortId;
            }

            public void setShortId(String shortId) {
                this.shortId = shortId;
            }

            public String getIsTest() {
                return isTest;
            }

            public void setIsTest(String isTest) {
                this.isTest = isTest;
            }
        }

    }

}