package com.skysoft.vaultlogic.clients.impl.device;

import com.skysoft.vaultlogic.clients.api.device.PrinterDeviceClient;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import com.skysoft.vaultlogic.clients.mappers.BaseInfoMapper;
import com.skysoft.vaultlogic.clients.mappers.device.PrinterDeviceMapper;
import com.skysoft.vaultlogic.clients.requests.printerDevice.PrintReceiptRequest;
import com.skysoft.vaultlogic.clients.requestModels.printerDevice.PrintReceiptBody;
import com.skysoft.vaultlogic.clients.responces.BaseResponse;
import com.skysoft.vaultlogic.clients.responces.device.printer.CreateReceiptUrlResponse;
import com.skysoft.vaultlogic.clients.responseModels.BaseInfo;
import com.skysoft.vaultlogic.clients.responseModels.device.printer.CreateReceiptUrlInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.clients.MayaHeaders.X_TOKEN_HEADER;


@Service
public class PrinterDeviceClientImpl implements PrinterDeviceClient {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;
    private final BaseInfoMapper baseInfoMapper;
    private final PrinterDeviceMapper printerMapper;

    @Autowired
    public PrinterDeviceClientImpl(MayaProperties mayaProperties,
                                   OAuth2RestTemplate oAuth2RestTemplate,
                                   BaseInfoMapper baseInfoMapper,
                                   PrinterDeviceMapper printerMapper) {
        this.mayaProperties = mayaProperties;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
        this.baseInfoMapper = baseInfoMapper;
        this.printerMapper = printerMapper;
    }

    @Override
    public CreateReceiptUrlInfo createReceipt(String xToken) {
        try {
            ResponseEntity<CreateReceiptUrlResponse> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getCreateReceiptUrl()), CreateReceiptUrlResponse.class);
            return printerMapper.responseToCreateReceiptUrlInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public BaseInfo printReceipt(String xToken, PrintReceiptBody printReceipt) {
        try {
            ResponseEntity<BaseResponse> exchange = oAuth2RestTemplate.exchange(buildPrintReceiptRequestEntity(xToken, printReceipt), BaseResponse.class);
            return baseInfoMapper.responseToBaseInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(X_TOKEN_HEADER, xToken)
                .build();
    }

    private RequestEntity<PrintReceiptRequest> buildPrintReceiptRequestEntity(String xToken, PrintReceiptBody printReceipt) {
        return RequestEntity.post(URI.create(mayaProperties.getPrintReceiptUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(PrintReceiptRequest.of(printReceipt));
    }

}