package com.skysoft.vaultlogic.clients.impl.device;

import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import com.skysoft.vaultlogic.web.maya.clients.api.device.CashDeviceClient;
import com.skysoft.vaultlogic.clients.mappers.BaseInfoMapper;
import com.skysoft.vaultlogic.clients.mappers.device.CashDeviceMapper;
import com.skysoft.vaultlogic.clients.requestModels.cashDevice.DispenseCashBody;
import com.skysoft.vaultlogic.clients.requests.cashDevice.DispenseCashRequest;
import com.skysoft.vaultlogic.clients.requests.cashDevice.EnableCashAcceptorRequest;
import com.skysoft.vaultlogic.clients.requests.cashDevice.GetDispensableAmountRequest;
import com.skysoft.vaultlogic.clients.requestModels.cashDevice.EnableCashAcceptorBody;
import com.skysoft.vaultlogic.clients.requestModels.cashDevice.GetDispensableAmountBody;
import com.skysoft.vaultlogic.clients.responces.BaseResponse;
import com.skysoft.vaultlogic.web.maya.clients.responces.device.cash.*;
import com.skysoft.vaultlogic.clients.responseModels.BaseInfo;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.device.cash.*;
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
    private final BaseInfoMapper baseInfoMapper;
    private final CashDeviceMapper cashMapper;

    @Autowired
    public CashDeviceClientImpl(MayaProperties mayaProperties,
                                OAuth2RestTemplate oAuth2RestTemplate,
                                BaseInfoMapper baseInfoMapper,
                                CashDeviceMapper cashMapper) {
        this.mayaProperties = mayaProperties;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
        this.baseInfoMapper = baseInfoMapper;
        this.cashMapper = cashMapper;
    }

    @Override
    public CashDeviceStatusInfo getCashDeviceStatus(String xToken) {
        try {
            ResponseEntity<CashDeviceStatusResponse> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getCashDeviceStatusUrl()), CashDeviceStatusResponse.class);
            return cashMapper.responseToCashDeviceStatusInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RecyclerDeviceStatusInfo getRecyclerDeviceStatus(String xToken) {
        try {
            ResponseEntity<RecyclerDeviceStatusResponse> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getRecyclerDeviceStatusUrl()), RecyclerDeviceStatusResponse.class);
            return cashMapper.responseToRecyclerDeviceStatusInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public EnableCashAcceptorInfo enableCashAcceptor(String xToken, EnableCashAcceptorBody enableCashAcceptor) {
        try {
            ResponseEntity<EnableCashAcceptorResponse> exchange = oAuth2RestTemplate.exchange(buildEnableCashAcceptorRequestEntity(xToken, enableCashAcceptor), EnableCashAcceptorResponse.class);
            return cashMapper.responseToEnableCashAcceptorInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public DisableCashAcceptorInfo disableCashAcceptor(String xToken) {
        try {
            ResponseEntity<DisableCashAcceptorResponse> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getDisableCashAcceptorUrl()), DisableCashAcceptorResponse.class);
            return cashMapper.responseToDisableCashAcceptorInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public GetDispensableAmountInfo getDispensableAmount(String xToken, GetDispensableAmountBody getDispensableAmount) {
        try {
            ResponseEntity<GetDispensableAmountResponse> exchange = oAuth2RestTemplate.exchange(buildGetDispensableAmountRequestEntity(xToken, getDispensableAmount), GetDispensableAmountResponse.class);
            return  cashMapper.responseToGetDispensableAmountInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public BaseInfo dispenseCash(String xToken, DispenseCashBody dispenseCash) {
        try {
            ResponseEntity<BaseResponse> exchange = oAuth2RestTemplate.exchange(buildDispenseCashRequestEntity(xToken, dispenseCash), BaseResponse.class);
            return baseInfoMapper.responseToBaseInfo(exchange.getBody());
        } catch (Exception e) {
            throw e;
        }
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(getxTokenHeader(), xToken)
                .build();
    }

    private RequestEntity<EnableCashAcceptorRequest> buildEnableCashAcceptorRequestEntity(String xToken, EnableCashAcceptorBody enableCashAcceptor) {
        return RequestEntity.post(URI.create(mayaProperties.getEnableCashAcceptorUrl()))
                .header(getxTokenHeader(), xToken)
                .body(EnableCashAcceptorRequest.of(enableCashAcceptor));
    }

    private RequestEntity<GetDispensableAmountRequest> buildGetDispensableAmountRequestEntity(String xToken, GetDispensableAmountBody getDispensableAmount) {
        return RequestEntity.post(URI.create(mayaProperties.getDispensableAmountUrl()))
                .header(getxTokenHeader(), xToken)
                .body(GetDispensableAmountRequest.of(getDispensableAmount));
    }

    private RequestEntity<DispenseCashRequest> buildDispenseCashRequestEntity(String xToken, DispenseCashBody dispenseCash) {
        return RequestEntity.post(URI.create(mayaProperties.getDispenseCashUrl()))
                .header(getxTokenHeader(), xToken)
                .body(DispenseCashRequest.of(dispenseCash));
    }

}