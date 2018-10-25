package com.skysoft.vaultlogic.postback.impl.handlers;

import com.skysoft.vaultlogic.postback.api.AbstractEventHandler;
import com.skysoft.vaultlogic.postback.api.EventEmptyResponse;
import com.skysoft.vaultlogic.postback.impl.factories.markers.CameraActionHandler;
import com.skysoft.vaultlogic.postback.impl.protocol.data.CameraPhoto;
import com.skysoft.vaultlogic.postback.mapper.DataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Profile("cloud")
public class CameraPhotoActionHandler extends AbstractEventHandler<CameraPhoto, EventEmptyResponse> implements CameraActionHandler {

    private static final String CAMERA_DEVICE_HANDLER = "camera-photo";

    @Autowired
    protected CameraPhotoActionHandler(DataMapper dataMapper) {
        super(CameraPhoto.class, dataMapper);
    }

    @Override
    @Transactional(readOnly = true)
    public EventEmptyResponse handleEvent(CameraPhoto eventData, String xToken) {
        log.info("[x] ---> Camera event: {} for session: {}", eventData, xToken);
        return new EventEmptyResponse();
    }

    @Override
    public String getName() {
        return CAMERA_DEVICE_HANDLER;
    }

}
