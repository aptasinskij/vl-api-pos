package com.skysoft.vaultlogic.web.maya.clients.api;

import com.skysoft.vaultlogic.web.maya.clients.responce.customer.GetCustomerInformationResponse;
import com.skysoft.vaultlogic.web.maya.clients.responce.customer.SetCustomerInSessionResponse;
import org.springframework.http.ResponseEntity;

public interface CustomerClient {

    ResponseEntity<SetCustomerInSessionResponse> setCustomerInSession(String xToken);

    ResponseEntity<GetCustomerInformationResponse> getCustomerInformation(String xToken);

}
