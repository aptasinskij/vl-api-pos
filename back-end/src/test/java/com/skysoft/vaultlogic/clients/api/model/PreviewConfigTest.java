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
public class PreviewConfigTest {

    @Autowired
    private JacksonTester<PreviewConfig> json;

    @Test
    public void testSerialization() throws IOException {
        PreviewConfig previewConfig = PreviewConfig.of(true, true);
        assertThat(json.write(previewConfig)).isEqualToJson("previewConfig.json");
    }

}