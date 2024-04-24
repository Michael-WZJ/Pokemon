package com.michaelj.infrastructure.constant;

import com.michaelj.domain.Code;

public interface BaseExceptionEnum {
    Code getCode();

    String getMessage();
}
