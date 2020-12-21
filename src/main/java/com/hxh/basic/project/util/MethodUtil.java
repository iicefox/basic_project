package com.hxh.basic.project.util;

/**
 * @author 黄训辉
 * Date: Created in 18/5/20 下午4:49
 * Utils: Intellij Idea
 * Description: 获取当前方法和行号
 */
public class MethodUtil {

    /**
     * 私有化工具类 防止被实例化
     */
    private MethodUtil() {
    }

    public static String getLineInfo() {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        return ste.getMethodName() + " -> " + ste.getLineNumber() + "行";
    }

}
