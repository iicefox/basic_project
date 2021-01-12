package com.hxh.basic.project.util;


import com.hxh.basic.project.enums.ResultEnum;
import com.hxh.basic.project.vo.ResultVO;

/**
 * 返回数据工具类
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 15:02
 * <p>
 * Copyright  Copyright 2021 yomu Inc.
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class ResultVOUtil {

    /**
     * 私有化工具类 防止被实例化
     * j
     */
    private ResultVOUtil() {
    }

    /**
     * 成功
     *
     * @param object 需要返回的数据
     * @return data
     */
    public static ResultVO success(Object object) {
        ResultVO result = new ResultVO();
        result.setCode(ResultEnum.OK.getCode());
        result.setMessage(ResultEnum.OK.getMessage());
        result.setData(object);
        return result;
    }

    /**
     * 成功
     *
     * @return 返回空
     */
    public static ResultVO success() {
        return success(null);
    }

    /**
     * 错误
     *
     * @param resultEnum 错误枚举类
     * @return 错误信息
     */
    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO result = new ResultVO();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }

    /**
     * 错误
     *
     * @param code 状态码
     * @param msg  消息
     * @return ResultBean
     */
    public static ResultVO error(Integer code, String msg) {
        ResultVO result = new ResultVO();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    /**
     * 错误
     *
     * @param msg 错误信息
     * @return ResultBean
     */
    public static ResultVO error(String msg) {
        return error(-1, msg);
    }

}
