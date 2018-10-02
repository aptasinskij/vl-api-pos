package com.skysoft.vaultlogic.web.maya.clients.impl.device;

import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import com.skysoft.vaultlogic.web.maya.clients.api.device.CashDeviceClient;
import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import com.skysoft.vaultlogic.web.maya.clients.responce.device.cash.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.web.maya.MayaHeaders.getxTokenHeader;

@Service
public class CashDeviceClientImpl implements CashDeviceClient {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    public CashDeviceClientImpl(MayaProperties mayaProperties,
                                OAuth2RestTemplate oAuth2RestTemplate) {
        this.mayaProperties = mayaProperties;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    @Override
    public ResponseEntity<CashDeviceStatusResponse> getCashDeviceStatus(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getCashDeviceStatusUrl()), CashDeviceStatusResponse.class);
    }

    @Override
    public ResponseEntity<RecyclerDeviceStatusResponse> getRecyclerDeviceStatus(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getRecyclerDeviceStatusUrl()), RecyclerDeviceStatusResponse.class);
    }

    @Override
    public ResponseEntity<EnableCashAcceptorResponse> enableCashAcceptor(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getEnableCashAcceptorUrl()), EnableCashAcceptorResponse.class);
    }

    @Override
    public ResponseEntity<DisableCashAcceptorResponse> disableCashAcceptor(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getDisableCashAcceptorUrl()), DisableCashAcceptorResponse.class);
    }

    @Override
    public ResponseEntity<GetDispensableAmountResponse> getDispensableAmount(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getDispensableAmountUrl()), GetDispensableAmountResponse.class);
    }

    @Override
    public ResponseEntity<BaseResponse> dispenseCash(String xToken) {
        return oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getDispenseCashUrl()), BaseResponse.class);
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(getxTokenHeader(), xToken)
                .build();
    }

}