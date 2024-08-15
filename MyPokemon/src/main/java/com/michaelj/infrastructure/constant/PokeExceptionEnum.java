package com.michaelj.infrastructure.constant;

import com.michaelj.domain.Code;

public enum PokeExceptionEnum implements BaseExceptionEnum{
    CODE_REPEAT_FAIL(Code.PROJECT_VALIDATE_ERR, "宝可梦编号重复"),
    NAME_REPEAT_FAIL(Code.PROJECT_VALIDATE_ERR, "宝可梦名称重复"),
    EVOL_REPEAT_FAIL(Code.PROJECT_VALIDATE_ERR, "已有该进化"),
    EVOL_NOT_FOUND_FAIL(Code.PROJECT_VALIDATE_ERR, "该进化链不存在"),
    EVOL_PARAM_FAIL(Code.PROJECT_VALIDATE_ERR, "进化链参数不能为空"),
    EVOL_PATERNAL_NOT_FOUND_FAIL(Code.PROJECT_VALIDATE_ERR, "未找到进化型: {0}"),
    EXCEL_PARSE_FAIL(Code.PROJECT_BUSINESS_ERR, "Excel文件解析错误"),
    EXCEL_EXPORT_FAIL(Code.PROJECT_BUSINESS_ERR, "导出失败"),
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
