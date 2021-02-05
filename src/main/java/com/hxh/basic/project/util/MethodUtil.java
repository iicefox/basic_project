package com.hxh.basic.project.util;

/**
 * 获取当前方法和行号
 *
 * @author yomu
 * @version 1.0
 * @date 2021/1/4 15:02
 * <p>
 * Copyright 2021 yomu Inc.
 */
public class MethodUtil {

    public static String getLineInfo() {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        return ste.getClassName() + "#" + ste.getMethodName() + " -> " + ste.getLineNumber() + "行";
    }

    /**
     * 私有化工具类 防止被实例化
     */
    private MethodUtil() {
    }

}
