package com.skysoft.vaultlogic.web.maya.clients.impl.device;

import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import com.skysoft.vaultlogic.web.maya.clients.api.device.PrinterDeviceClient;
import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import com.skysoft.vaultlogic.web.maya.clients.responce.device.printer.CreateReceiptUrlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.web.maya.MayaHeaders.getxTokenHeader;

@Service
public class PrinterDeviceClientImpl implements PrinterDeviceClient {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    public PrinterDeviceClientImpl(MayaProperties mayaProperties,
                                   OAuth2RestTemplate oAuth2RestTemplate) {
        this.mayaProperties = mayaProperties;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    @Override
    public ResponseEntity<CreateReceiptUrlResponse> createReceipt(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getCreateReceiptUrl()), CreateReceiptUrlResponse.class);
    }

    @Override
    public ResponseEntity<BaseResponse> printReceipt(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getPrintReceiptUrl()), BaseResponse.class);
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(getxTokenHeader(), xToken)
                .build();
    }

}
