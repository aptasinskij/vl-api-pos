package com.skysoft.vaultlogic.clients.api.model;

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
public class KioskIdTest {

    @Autowired
    private JacksonTester<KioskId> json;

    @Test
    public void testSerialization() throws IOException {
        KioskId kioskId = KioskId.of("kiosk_id");
        assertThat(json.write(kioskId)).isEqualToJson("kioskId.json");
    }

}