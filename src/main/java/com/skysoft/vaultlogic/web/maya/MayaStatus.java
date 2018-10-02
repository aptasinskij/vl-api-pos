package com.skysoft.vaultlogic.web.maya;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum MayaStatus {

    CORRECT_CODE(""),
    ACCESS_TOKEN_NOT_FOUND("401"),
    API_CALL_NOT_FOUND("404"),
    NONCE_CONFLICT("409"),
    INVALID_HEADERS_LENGTH("411"),
    INVALID_ACCESS_TOKEN_LENGTH("413"),
    INVALID_APPLICATION_ID("427"),
    SESSION_IS_EXPIRED("428"),
    TIME_LIMIT_IS_REACHED("429"),
    SESSION_IS_NOT_FOUND("432"),
    DEVICE_IS_NOT_FOUND("433"),
    APPLICATION_IS_NOT_FOUND("434"),
    OPEN_MAYA_SERVER_ERROR("500"),
    EXCEPTION_IN_A_REQUEST_HANDLER("500"),
    UNEXPECTED_CODE(),
    ;

    private String errorCode;

    MayaStatus(String errorCode) {
        this.errorCode = errorCode;
    }

    public static MayaStatus getStatus(String code) {
        if (code.equals(CORRECT_CODE.errorCode)) return CORRECT_CODE;
        if (code.equals(ACCESS_TOKEN_NOT_FOUND.errorCode)) return ACCESS_TOKEN_NOT_FOUND;
        if (code.equals(API_CALL_NOT_FOUND.errorCode)) return ACCESS_TOKEN_NOT_FOUND;
        if (code.equals(NONCE_CONFLICT.errorCode)) return NONCE_CONFLICT;
        if (code.equals(INVALID_HEADERS_LENGTH.errorCode)) return INVALID_HEADERS_LENGTH;
        if (code.equals(INVALID_ACCESS_TOKEN_LENGTH.errorCode)) return INVALID_ACCESS_TOKEN_LENGTH;
        if (code.equals(INVALID_APPLICATION_ID.errorCode)) return INVALID_APPLICATION_ID;
        if (code.equals(SESSION_IS_EXPIRED.errorCode)) return SESSION_IS_EXPIRED;
        if (code.equals(TIME_LIMIT_IS_REACHED.errorCode)) return TIME_LIMIT_IS_REACHED;
        if (code.equals(SESSION_IS_NOT_FOUND.errorCode)) return SESSION_IS_NOT_FOUND;
        if (code.equals(DEVICE_IS_NOT_FOUND.errorCode)) return DEVICE_IS_NOT_FOUND;
        if (code.equals(APPLICATION_IS_NOT_FOUND.errorCode)) return APPLICATION_IS_NOT_FOUND;
        if (code.equals(OPEN_MAYA_SERVER_ERROR.errorCode)) return OPEN_MAYA_SERVER_ERROR;
        if (code.equals(EXCEPTION_IN_A_REQUEST_HANDLER.errorCode)) return EXCEPTION_IN_A_REQUEST_HANDLER;
     return UNEXPECTED_CODE;
    }

}