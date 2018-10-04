package com.skysoft.vaultlogic.web.maya.clients.responseModels.device.camera;

import com.skysoft.vaultlogic.web.maya.clients.responseModels.BaseInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class StartPreviewInfo extends BaseInfo {

    private String port;
    private String url;
    private String href;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}