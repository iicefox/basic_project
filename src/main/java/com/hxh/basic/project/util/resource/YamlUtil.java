package com.hxh.basic.project.util.resource;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.hxh.basic.project.exception.CustomException;

import java.io.File;
import java.io.IOException;

/**
 * yaml工具类:转换yaml
 *
 * @author yomu
 * @version 1.0
 * @create 2020/12/15 13:21
 */
public class YamlUtil {
    /**
     * yaml文件后缀正则表达式
     */
    private static final String YAML_EXT_REG = "^ya?ml$";

    private static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    private YamlUtil() {
    }

    /**
     * yaml字符串转指定类型对象
     *
     * @param content    yaml字符串
     * @param targetType 目标类型
     * @param <T>
     * @return 指定类型对象
     * @throws JsonProcessingException
     */
    public static <T> T toObject(String content, Class<T> targetType) throws JsonProcessingException {
        if (targetType == null) {
            throw new UtilException("TargetType could not be null");
        }
        if (StrUtil.isBlank(content)) {
            throw new UtilException("No content to map due to content is blank");
        }
        return mapper.readValue(content, targetType);
    }

    /**
     * yaml字符串转Object
     *
     * @param content yaml字符串
     * @return
     * @throws JsonProcessingException
     */
    public static Object toObject(String content) throws JsonProcessingException {
        return toObject(content, Object.class);
    }

    /**
     * yaml文件转指定类型对象
     *
     * @param resource   yaml文件
     * @param targetType 目标类型
     * @param <T>
     * @return 指定类型对象
     * @throws CustomException
     * @throws IOException
     */
    public static <T> T readToObject(File resource, Class<T> targetType) throws IOException {
        if (targetType == null) {
            throw new UtilException("TargetType could not be null");
        }
        if (!FileUtil.isFile(resource)) {
            throw new UtilException("File not found");
        }
        if (!Validator.isMatchRegex(YAML_EXT_REG, FileTypeUtil.getType(resource))) {
            throw new UtilException("File type is not yaml");
        }
        if (FileUtil.isEmpty(resource)) {
            throw new UtilException("No content to map due to content is blank");
        }
        return mapper.readValue(resource, targetType);
    }

    /**
     * yaml文件转Object
     *
     * @param resource yaml文件
     * @return
     * @throws CustomException
     * @throws IOException
     */
    public static Object readToObject(File resource) throws IOException, CustomException {
        return readToObject(resource, Object.class);
    }

    /**
     * 读取yaml文件为指定类型对象
     *
     * @param resource   yaml文件
     * @param targetType 目标类型
     * @param <T>
     * @return 指定类型对象
     * @throws CustomException
     * @throws IOException
     */
    public static <T> T readToObject(String resource, Class<T> targetType) throws IOException, CustomException {
        if (StrUtil.isBlank(resource)) {
            throw new UtilException("File not found");
        }
        return readToObject(FileUtil.newFile(FileUtil.getAbsolutePath(resource)), targetType);
    }

    /**
     * 读取yaml文件为Object
     *
     * @param resource yaml文件
     * @return
     * @throws CustomException
     * @throws IOException
     */
    public static Object readToObject(String resource) throws IOException, CustomException {
        return readToObject(resource, Object.class);
    }

    /**
     * 对象转Yaml字符串
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static String writeYamlAsString(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    /**
     * 对象转Yaml文件
     * @param obj
     * @param file Yaml文件
     * @throws IOException
     */
    public static void writeYaml(Object obj, File file) throws IOException {
        mapper.writeValue(file, obj);
    }

    /**
     * 对象转Yaml文件
     * @param obj
     * @param file Yaml文件
     * @throws IOException
     */
    public static void writeYaml(Object obj, String file) throws IOException {
        writeYaml(obj, FileUtil.newFile(file));
    }

}
