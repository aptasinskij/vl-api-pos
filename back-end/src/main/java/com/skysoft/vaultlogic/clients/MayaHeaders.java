package com.skysoft.vaultlogic.clients;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MayaHeaders {

    private static final String X_TOKEN_HEADER = "X-Token";
    private static final String X_NONCE_HEADER = "X-Nonce";

    public static String getxTokenHeader() {
        return X_TOKEN_HEADER;
    }

    public static String getxNonceHeader() {
        return X_NONCE_HEADER;
    }

}
