package com.skysoft.vaultlogic.clients.api.model.responses;

import com.skysoft.vaultlogic.clients.api.model.Cassette;
import com.skysoft.vaultlogic.clients.api.model.RecyclerStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

import static com.skysoft.vaultlogic.clients.api.model.Cassette.Type.cash;
import static com.skysoft.vaultlogic.clients.api.model.Cassette.Type.coin;
import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@RunWith(SpringRunner.class)
public class RecyclerStatusTest {

    @Autowired
    private JacksonTester<RecyclerStatus> json;

    @Test
    public void testDeserialization() throws IOException {
        RecyclerStatus recyclerStatus = new RecyclerStatus();

        recyclerStatus.setEnabled(true);
        recyclerStatus.setCurrentAmount(BigDecimal.ZERO);
        recyclerStatus.setCurrentCount(BigDecimal.ZERO);

        Cassette cassette1 = new Cassette();
        cassette1.setId("id_1");
        cassette1.setCount(BigDecimal.ZERO);
        cassette1.setDenomination("denomination_1");
        cassette1.setAmount(BigDecimal.ZERO);
        cassette1.setMaxCount(BigDecimal.ZERO);
        cassette1.setType(cash);

        Cassette cassette2 = new Cassette();
        cassette2.setId("id_2");
        cassette2.setCount(BigDecimal.ONE);
        cassette2.setDenomination("denomination_2");
        cassette2.setAmount(BigDecimal.ONE);
        cassette2.setMaxCount(BigDecimal.ONE);
        cassette2.setType(coin);

        recyclerStatus.setCassettes(Arrays.asList(cassette1, cassette2));

        recyclerStatus.setErrorCode("code");
        recyclerStatus.setStatus("status");

        assertThat(json.write(recyclerStatus)).isEqualToJson("recyclerStatus.json");
    }

}