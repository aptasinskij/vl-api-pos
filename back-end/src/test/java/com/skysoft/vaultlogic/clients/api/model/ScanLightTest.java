package com.skysoft.vaultlogic.clients.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static com.skysoft.vaultlogic.clients.api.model.Light.white;
import static org.assertj.core.api.Assertions.assertThat;


@JsonTest
@RunWith(SpringRunner.class)
public class ScanLightTest {

    @Autowired
    private JacksonTester<ScanLight> json;

    @Test
    public void testSerialization() throws IOException {
        ScanLight scanLight = ScanLight.of(white);
        assertThat(json.write(scanLight)).isEqualToJson("scanLight.json");
    }

}