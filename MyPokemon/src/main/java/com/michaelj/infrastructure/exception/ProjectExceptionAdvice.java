package com.michaelj.infrastructure.exception;

import com.michaelj.domain.Code;
import com.michaelj.domain.base.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//TODO 异常处理器
@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException ex) {
        return new Result(ex.getCode(), null, ex.getMessage());
    }

    /**
     * 记录日志（错误堆栈）
     * 发送邮件给开发人员
     * 发送短信给运维人员
     */
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException ex) {
        return new Result(ex.getCode(), null, ex.getMessage());
    }

    /**
     * 记录日志（错误堆栈）
     * 发送邮件给开发人员
     * 发送短信给运维人员
     */
    @ExceptionHandler(Exception.class) // 捕获所有异常
    public Result doException(Exception e) {
        return new Result(Code.SYSTEM_UNKNOWN_ERR.getCode(), null, "系统繁忙，请联系管理员！");
    }
}
