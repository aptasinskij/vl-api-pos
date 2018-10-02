package com.skysoft.vaultlogic.web.maya.clients.responce.device.cash;

import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisableCashAcceptorResponse extends BaseResponse {

    private Boolean disabled;

}