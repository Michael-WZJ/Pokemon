package com.michaelj.infrastructure.constant;

import com.michaelj.domain.Code;

public enum PokeExceptionEnum implements BaseExceptionEnum {
    COMMON_100001(Code.PROJECT_VALIDATE_ERR, "id不能为空！"),
    COMMON_100002(Code.SAVE_ERR, "新增 存在重复数据！"),
    COMMON_100003(Code.GET_ERR, "未查询到数据！"),
    COMMON_100004(Code.GET_ERR, "查询到多条数据！"),


    CODE_REPEAT_FAIL(Code.PROJECT_VALIDATE_ERR, "宝可梦编号重复"),
    NAME_REPEAT_FAIL(Code.PROJECT_VALIDATE_ERR, "宝可梦名称重复"),
    EVOL_REPEAT_FAIL(Code.PROJECT_VALIDATE_ERR, "已有该进化"),
    EVOL_NOT_FOUND_FAIL(Code.PROJECT_VALIDATE_ERR, "该进化链不存在"),
    EVOL_PARAM_FAIL(Code.PROJECT_VALIDATE_ERR, "进化链参数不能为空"),
    EVOL_PATERNAL_NOT_FOUND_FAIL(Code.PROJECT_VALIDATE_ERR, "未找到进化型: {0}"),


    CHILDERN_EXIST_ERR(Code.DELETE_ERR, "存在子配置！"),


    FILE_PATH_BLANK_ERR(Code.PROJECT_BUSINESS_ERR, "文件路径不能为空"),
    RESOURCE_ATTACH_ERR(Code.PROJECT_BUSINESS_ERR, "读取资源异常"),


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
