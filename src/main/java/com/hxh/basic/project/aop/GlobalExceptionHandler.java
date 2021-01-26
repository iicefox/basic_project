package com.hxh.basic.project.aop;

import com.hxh.basic.project.enums.ResultEnum;
import com.hxh.basic.project.exception.CustomRuntimeException;
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

import javax.validation.ConstraintViolationException;
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
     * 拦截 requestParam/PathVariable 参数校验
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.OK)
    public ResultVO handleConstraintViolationException(ConstraintViolationException e) {
        return ResultVOUtil.error(Objects.requireNonNull(e.getLocalizedMessage()));
    }

    /**
     * 参数格式错误
     *
     * @param e 参数类型不匹配 比如 字符串“a” 不能匹配 Integer类型
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultVO methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error(e.getLocalizedMessage());
        return ResultVOUtil.error(ResultEnum.ARGUMENT_TYPE_MISMATCH);
    }

    /**
     * 参数格式错误
     *
     * @param e <p>在页面上传到后台的参数类型与页面的contentType类型不匹配,
     *          如 post参数要求是一个如下json格式化对象, 而实际参数传入 “abc”
     *          {
     *          "name":" sdsdsd",
     *          "age": 19
     *          }
     *          <p/>
     *          <p>通常出现在rpc调用时，被@RequestBody 修饰的请求参数实体类没有实现序列化接口<p/>
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
