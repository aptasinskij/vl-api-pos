package com.skysoft.vaultlogic.clients.api.model.responses;

import com.skysoft.vaultlogic.clients.api.model.KioskDevice;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@RunWith(SpringRunner.class)
public class KioskDeviceTest {

    @Autowired
    private JacksonTester<KioskDevice> json;

    @Test
    public void testDeserialization() throws IOException {
        KioskDevice kioskDevice = new KioskDevice();
        kioskDevice.setDeviceId("device_id");
        kioskDevice.setDeviceCurrency("device_currency");
        kioskDevice.setInfo("info");
        kioskDevice.setShortId("short_id");
        kioskDevice.setIsTest("is_test");
        kioskDevice.setLocationId("location_id");
        kioskDevice.setBusinessName("business_name");
        kioskDevice.setAddress1("address1");
        kioskDevice.setAddress2("address2");
        kioskDevice.setFormattedAddress("formatted_address");
        kioskDevice.setCity("city");
        kioskDevice.setState("state");
        kioskDevice.setZip("zip");
        kioskDevice.setCountry("country");
        kioskDevice.setLongitude("longitude");
        kioskDevice.setLatitude("latitude");
        kioskDevice.setHidden("hidden");
        kioskDevice.setDescription("description");
        kioskDevice.setTimezone("timezone");
        kioskDevice.setErrorCode("code");
        kioskDevice.setStatus("status");
        assertThat(json.write(kioskDevice)).isEqualToJson("kioskDevice.json");
    }

}