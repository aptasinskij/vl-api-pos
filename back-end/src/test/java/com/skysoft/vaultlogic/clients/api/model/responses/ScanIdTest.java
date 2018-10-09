package com.skysoft.vaultlogic.clients.api.model.responses;

import com.skysoft.vaultlogic.clients.api.model.ScanId;
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
public class ScanIdTest {

    @Autowired
    private JacksonTester<ScanId> json;

    @Test
    public void testDeserialization() throws IOException {
        ScanId scanId = new ScanId();
        scanId.setId("scan_id");
        scanId.setErrorCode("code");
        scanId.setStatus("status");
        assertThat(json.read("scanId.json")).isEqualTo(scanId);
    }

}