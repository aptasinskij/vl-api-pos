package com.skysoft.vaultlogic.clients.api.model.responses;

import com.skysoft.vaultlogic.clients.api.model.KioskDevice;
import com.skysoft.vaultlogic.clients.api.model.KiosksInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@RunWith(SpringRunner.class)
public class KiosksInfoTest {

    @Autowired
    private JacksonTester<KiosksInfo> json;

    @Test
    public void testDeserialization() throws IOException {
        KiosksInfo kioskInfo = new KiosksInfo();

        KioskDevice kioskDevice1 = new KioskDevice();
        kioskDevice1.setDeviceId("device_id_1");
        kioskDevice1.setDeviceCurrency("device_currency_1");
        kioskDevice1.setInfo("info_1");
        kioskDevice1.setShortId("short_id_1");
        kioskDevice1.setIsTest("is_test_1");
        kioskDevice1.setLocationId("location_id_1");
        kioskDevice1.setBusinessName("business_name_1");
        kioskDevice1.setAddress1("address1_1");
        kioskDevice1.setAddress2("address2_1");
        kioskDevice1.setFormattedAddress("formatted_address_1");
        kioskDevice1.setCity("city_1");
        kioskDevice1.setState("state_1");
        kioskDevice1.setZip("zip_1");
        kioskDevice1.setCountry("country_1");
        kioskDevice1.setLongitude("longitude_1");
        kioskDevice1.setLatitude("latitude_1");
        kioskDevice1.setHidden("hidden_1");
        kioskDevice1.setDescription("description_1");
        kioskDevice1.setTimezone("timezone_1");

        KioskDevice kioskDevice2 = new KioskDevice();
        kioskDevice2.setDeviceId("device_id_2");
        kioskDevice2.setDeviceCurrency("device_currency_2");
        kioskDevice2.setInfo("info_2");
        kioskDevice2.setShortId("short_id_2");
        kioskDevice2.setIsTest("is_test_2");
        kioskDevice2.setLocationId("location_id_2");
        kioskDevice2.setBusinessName("business_name_2");
        kioskDevice2.setAddress1("address1_2");
        kioskDevice2.setAddress2("address2_2");
        kioskDevice2.setFormattedAddress("formatted_address_2");
        kioskDevice2.setCity("city_2");
        kioskDevice2.setState("state_2");
        kioskDevice2.setZip("zip_2");
        kioskDevice2.setCountry("country_2");
        kioskDevice2.setLongitude("longitude_2");
        kioskDevice2.setLatitude("latitude_2");
        kioskDevice2.setHidden("hidden_2");
        kioskDevice2.setDescription("description_2");
        kioskDevice2.setTimezone("timezone_2");

        kioskInfo.setDevices(Arrays.asList(kioskDevice1, kioskDevice2));
        kioskInfo.setErrorCode("code");
        kioskInfo.setStatus("status");
        assertThat(json.write(kioskInfo)).isEqualToJson("kiosksInfo.json");
    }

}