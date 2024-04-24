package com.michaelj.infrastructure.exception;

import com.michaelj.domain.Code;
import com.michaelj.infrastructure.constant.BaseExceptionEnum;

import java.io.Serial;

public class BusinessException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -172305240455308613L;
    private Code code;

    public BusinessException(Code code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(Code code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(BaseExceptionEnum except) {
        super(except.getMessage());
        this.code = except.getCode();
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    /*                      Accessor and Mutator Methods                        */
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public Integer getCode() {
        return code.getCode();
    }

    public void setCode(Code code) {
        this.code = code;
    }
}
