package com.michaelj.exception;

import com.michaelj.domain.Code;

import java.io.Serial;

public class SystemException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 3811660617587630111L;
    private Code code;

    public SystemException(Code code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(Code code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
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
