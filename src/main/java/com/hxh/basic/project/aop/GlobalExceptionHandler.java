package com.hxh.basic.project.aop;

import com.hxh.basic.project.enums.ResultEnum;
import com.hxh.basic.project.exception.CustomRuntimeException;
import com.hxh.basic.project.util.MethodUtil;
import com.hxh.basic.project.util.ResultVOUtil;
import com.hxh.basic.project.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;


/**
 * 全局异常处理
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 11:02
 * <p>
 * Copyright  Copyright 2021 yomu Inc.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义异常
     */
    @ExceptionHandler(value = CustomRuntimeException.class)
    public ResultVO processException(CustomRuntimeException e) {
        log.error("位置:{} -> 错误信息:{}", e.getMethod(), e.getLocalizedMessage());
        return ResultVOUtil.error(Objects.requireNonNull(ResultEnum.getByCode(e.getCode())));
    }

    /**
     * 拦截表单参数校验
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BindException.class})
    public ResultVO bindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        return ResultVOUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /**
     * 拦截JSON参数校验
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO bindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return ResultVOUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /**
     * 参数格式错误
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultVO methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error(e.getLocalizedMessage());
        return ResultVOUtil.error(ResultEnum.ARGUMENT_TYPE_MISMATCH);
    }

    /**
     * 参数格式错误
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultVO httpMessageNotReadable(HttpMessageNotReadableException e) {
        log.error(e.getLocalizedMessage());
        return ResultVOUtil.error(ResultEnum.FORMAT_ERROR);
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultVO httpReqMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.error(e.getLocalizedMessage());
        return ResultVOUtil.error(ResultEnum.REQ_METHOD_NOT_SUPPORT);
    }

    /**
     * 通用异常
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public ResultVO exception(Exception e) {
        log.error(e.getLocalizedMessage(), e);
        return ResultVOUtil.error(ResultEnum.UNKNOWN_EXCEPTION);
    }
}
