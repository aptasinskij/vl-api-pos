package com.skysoft.vaultlogic.web.maya.clients.mappers;

import com.skysoft.vaultlogic.web.maya.clients.responseModels.customer.CustomerInfo;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.customer.CustomerSetInSessionInfo;
import com.skysoft.vaultlogic.web.maya.clients.responces.customer.GetCustomerInformationResponse;
import com.skysoft.vaultlogic.web.maya.clients.responces.customer.SetCustomerInSessionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerSetInSessionInfo responseToCustomerInSessionInfo(SetCustomerInSessionResponse response);

    SetCustomerInSessionResponse infoToSetCustomerInSessionResponse(CustomerSetInSessionInfo info);

    CustomerInfo responseToCustomerInfo(GetCustomerInformationResponse response);

    GetCustomerInformationResponse infoToGetCustomerInformationResponse(CustomerInfo info);
}