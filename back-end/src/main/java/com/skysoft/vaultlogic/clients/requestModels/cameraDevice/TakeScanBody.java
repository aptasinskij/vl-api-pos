package com.skysoft.vaultlogic.clients.requestModels.cameraDevice;

import com.skysoft.vaultlogic.clients.Light;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakeScanBody {

    private Light light;

}