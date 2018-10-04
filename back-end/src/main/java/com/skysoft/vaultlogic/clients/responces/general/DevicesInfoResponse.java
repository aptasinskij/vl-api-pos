package com.skysoft.vaultlogic.clients.responces.general;

import com.skysoft.vaultlogic.clients.responces.BaseResponse;
import com.skysoft.vaultlogic.clients.responces.device.DeviceInfoResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class DevicesInfoResponse extends BaseResponse {

    public List<DeviceInfoResponse> devices;

    public List<DeviceInfoResponse> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceInfoResponse> devices) {
        this.devices = devices;
    }

}