package com.skysoft.vaultlogic.clients.mappers.device;

import com.skysoft.vaultlogic.clients.responces.device.cash.*;
import com.skysoft.vaultlogic.clients.responseModels.device.cash.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CashDeviceMapper {

    CashDeviceStatusInfo responseToCashDeviceStatusInfo(CashDeviceStatusResponse response);

    CashDeviceStatusResponse infoToCashDeviceStatusResponse(CashDeviceStatusInfo info);

    RecyclerDeviceStatusInfo responseToRecyclerDeviceStatusInfo(RecyclerDeviceStatusResponse response);

    RecyclerDeviceStatusResponse infoToRecyclerDeviceStatusResponse(RecyclerDeviceStatusInfo info);

    EnableCashAcceptorInfo responseToEnableCashAcceptorInfo(EnableCashAcceptorResponse response);

    EnableCashAcceptorResponse infoToEnableCashAcceptorResponse(EnableCashAcceptorInfo info);

    DisableCashAcceptorInfo responseToDisableCashAcceptorInfo(DisableCashAcceptorResponse response);

    DisableCashAcceptorResponse infoToDisableCashAcceptorResponse(DisableCashAcceptorInfo info);

    GetDispensableAmountInfo responseToGetDispensableAmountInfo(GetDispensableAmountResponse response);

    GetDispensableAmountResponse infoToGetDispensableAmountResponse(GetDispensableAmountInfo info);

}