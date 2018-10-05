package com.skysoft.vaultlogic.clients.impl;

import com.skysoft.vaultlogic.clients.api.KioskCashDevices;
import com.skysoft.vaultlogic.clients.api.model.*;
import com.skysoft.vaultlogic.common.configuration.properties.MayaProperties;
import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.skysoft.vaultlogic.clients.MayaHeaders.X_TOKEN_HEADER;

@Service
@AllArgsConstructor
public class KioskCashDevicesHttpClient implements KioskCashDevices {

    private final MayaProperties mayaProperties;
    private final OAuth2RestTemplate oAuth2RestTemplate;

    @Override
    public CashDevicesStatus getStatus(String xToken) {
        try {
            ResponseEntity<CashDevicesStatus> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getCashDeviceStatusUrl()), CashDevicesStatus.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RecyclerStatus getRecyclerStatus(String xToken) {
        try {
            ResponseEntity<RecyclerStatus> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getRecyclerDeviceStatusUrl()), RecyclerStatus.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public CashAcceptorStatus enableCashAcceptor(String xToken, CashAcceptorConfig cashAcceptorConfig) {
        try {
            ResponseEntity<CashAcceptorStatus> exchange = oAuth2RestTemplate.exchange(buildEnableCashAcceptorRequestEntity(xToken, cashAcceptorConfig), CashAcceptorStatus.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public CashAcceptorStatus disableCashAcceptor(String xToken) {
        try {
            ResponseEntity<CashAcceptorStatus> exchange = oAuth2RestTemplate.exchange(buildRequestEntity(xToken, mayaProperties.getDisableCashAcceptorUrl()), CashAcceptorStatus.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public DispensableAmount getDispensableAmount(String xToken, DispensableAmount amountToDispense) {
        try {
            ResponseEntity<DispensableAmount> exchange = oAuth2RestTemplate.exchange(buildGetDispensableAmountRequestEntity(xToken, amountToDispense), DispensableAmount.class);
            return  exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public StatusCode dispenseCash(String xToken, DispenseCash dispenseCash) {
        try {
            ResponseEntity<StatusCode> exchange = oAuth2RestTemplate.exchange(buildDispenseCashRequestEntity(xToken, dispenseCash), StatusCode.class);
            return exchange.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    private RequestEntity<Void> buildRequestEntity(String xToken, String url) {
        return RequestEntity.post(URI.create(url))
                .header(X_TOKEN_HEADER, xToken)
                .build();
    }

    private RequestEntity<CashAcceptorConfig> buildEnableCashAcceptorRequestEntity(String xToken, CashAcceptorConfig acceptorConfig) {
        return RequestEntity.post(URI.create(mayaProperties.getEnableCashAcceptorUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(acceptorConfig);
    }

    private RequestEntity<DispensableAmount> buildGetDispensableAmountRequestEntity(String xToken, DispensableAmount amountToDispense) {
        return RequestEntity.post(URI.create(mayaProperties.getDispensableAmountUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(amountToDispense);
    }

    private RequestEntity<DispenseCash> buildDispenseCashRequestEntity(String xToken, DispenseCash dispenseCash) {
        return RequestEntity.post(URI.create(mayaProperties.getDispenseCashUrl()))
                .header(X_TOKEN_HEADER, xToken)
                .body(dispenseCash);
    }

}