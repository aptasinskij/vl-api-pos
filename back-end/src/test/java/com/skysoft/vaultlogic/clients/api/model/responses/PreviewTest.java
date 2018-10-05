package com.skysoft.vaultlogic.clients.api.model.responses;

import com.skysoft.vaultlogic.clients.api.model.Preview;
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
public class PreviewTest {

    @Autowired
    private JacksonTester<Preview> json;

    @Test
    public void testDeserialization() throws IOException {
        Preview preview = new Preview();
        preview.setPort("preview_port");
        preview.setUrl("preview_url");
        preview.setHref("preview_href");
        preview.setErrorCode("code");
        preview.setStatus("status");
        assertThat(json.write(preview)).isEqualToJson("preview.json");
    }

}