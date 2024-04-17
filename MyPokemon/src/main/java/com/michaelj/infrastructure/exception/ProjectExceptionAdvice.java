package com.michaelj.infrastructure.exception;

import com.michaelj.domain.Code;
import com.michaelj.domain.base.Result;
import com.michaelj.infrastructure.constant.BaseConst;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

//TODO 异常处理器
@RestControllerAdvice
public class ProjectExceptionAdvice {
    private static final String SPLIT= "; ";

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException ex) {
        return new Result(ex.getCode(), null, ex.getMessage());
    }

    /**
     * 处理 DTO Validated 校验异常
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        List<String> errMsgList = errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        String errMsg = errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(SPLIT));
        return new Result(Code.PROJECT_VALIDATE_ERR.getCode(), errMsgList, errMsgList.get(BaseConst.FIRST_ITEM));
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
//        return new Result(Code.SYSTEM_UNKNOWN_ERR.getCode(), null, "系统繁忙，请联系管理员！");
        return new Result(Code.SYSTEM_UNKNOWN_ERR.getCode(), null, e.getMessage());
    }
}
