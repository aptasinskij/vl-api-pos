package com.skysoft.vaultlogic.clients.api.model.responses;

import com.skysoft.vaultlogic.clients.api.model.ScannerStatus;
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
public class ScannerStatusTest {

    @Autowired
    private JacksonTester<ScannerStatus> json;

    @Test
    public void testDeserialization() throws IOException {
        ScannerStatus scannerStatus = new ScannerStatus();
        scannerStatus.setEnabled(true);
        scannerStatus.setQr("scanner_qr");
        scannerStatus.setErrorCode("code");
        scannerStatus.setStatus("status");
        assertThat(json.read("scannerStatus.json")).isEqualTo(scannerStatus);
    }

}