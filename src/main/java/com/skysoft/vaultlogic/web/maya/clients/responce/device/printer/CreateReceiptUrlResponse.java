package com.skysoft.vaultlogic.web.maya.clients.responce.device.printer;

import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReceiptUrlResponse extends BaseResponse {

    private String id;
    private String url;

}