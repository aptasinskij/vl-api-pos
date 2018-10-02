package com.skysoft.vaultlogic.web.maya.clients.api.device;

import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import com.skysoft.vaultlogic.web.maya.clients.responce.device.cash.*;
import org.springframework.http.ResponseEntity;

public interface CashDeviceClient {

    ResponseEntity<CashDeviceStatusResponse> getCashDeviceStatus(String xToken);

    ResponseEntity<RecyclerDeviceStatusResponse> getRecyclerDeviceStatus(String xToken);

    ResponseEntity<EnableCashAcceptorResponse> enableCashAcceptor(String xToken);

    ResponseEntity<DisableCashAcceptorResponse> disableCashAcceptor(String xToken);

    ResponseEntity<GetDispensableAmountResponse> getDispensableAmount(String xToken);

    ResponseEntity<BaseResponse> dispenseCash(String xToken);

}