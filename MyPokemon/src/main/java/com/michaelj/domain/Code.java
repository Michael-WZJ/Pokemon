package com.michaelj.domain;

public enum Code {
    SAVE_OK(10011), DELETE_OK(10021), UPDATE_OK(10031), GET_OK(10041),
    SAVE_ERR(10010), DELETE_ERR(10020), UPDATE_ERR(10030), GET_ERR(10040),
    SYSTEM_UNKNOWN_ERR(50001), SYSTEM_TIMEOUT_ERR(50002),
    PROJECT_VALIDATE_ERR(60001), PROJECT_BUSINESS_ERR(60002),
    ;

    private final int code;

    Code(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
