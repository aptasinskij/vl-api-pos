package com.skysoft.vaultlogic.web.maya.clients.responce.device.camera;

import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StartPreviewResponse extends BaseResponse {

    private String port;
    private String url;
    private String href;

}