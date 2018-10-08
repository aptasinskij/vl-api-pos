package com.skysoft.vaultlogic.clients.api.model.responses;

import com.skysoft.vaultlogic.clients.api.model.PhotoId;
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
public class PhotoIdTest {

    @Autowired
    private JacksonTester<PhotoId> json;

    @Test
    public void testDeserialization() throws IOException {
        PhotoId photoId = new PhotoId();
        photoId.setId("photo_id");
        photoId.setErrorCode("code");
        photoId.setStatus("status");
        assertThat(json.read("photoId.json")).isEqualTo(photoId);
    }

}