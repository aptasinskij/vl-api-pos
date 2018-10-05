package com.skysoft.vaultlogic.clients.api.model.responses;

import com.skysoft.vaultlogic.clients.api.model.ReceiptIdUrl;
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
public class ReceiptIdUrlTest {

    @Autowired
    private JacksonTester<ReceiptIdUrl> json;

    @Test
    public void testDeserialization() throws IOException {
        ReceiptIdUrl receiptIdUrl = new ReceiptIdUrl();
        receiptIdUrl.setId("receipt_id");
        receiptIdUrl.setUrl("receipt_url");
        receiptIdUrl.setErrorCode("code");
        receiptIdUrl.setStatus("status");
        assertThat(json.write(receiptIdUrl)).isEqualToJson("receiptIdUrl.json");
    }

}