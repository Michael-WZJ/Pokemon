package com.michaelj.infrastructure.constant;

import com.michaelj.domain.Code;

public enum PokeExceptionEnum implements BaseExceptionEnum{
    CODE_REPEAT_FAIL(Code.PROJECT_VALIDATE_ERR, "宝可梦编号重复"),
    NAME_REPEAT_FAIL(Code.PROJECT_VALIDATE_ERR, "宝可梦名称重复"),
    ;

    private final Code code;

    private final String message;

    PokeExceptionEnum (Code code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Code getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
