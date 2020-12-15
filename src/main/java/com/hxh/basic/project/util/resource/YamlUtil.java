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

public class YamlUtil {
    private static final String yaml_EXT_REG = "^ya?ml$";
    private static final String START_LINE = "---\n";
    public static final String RUBY_HASH = "!ruby/hash";

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
        if (content.startsWith(START_LINE)) {
            content = content.replace(START_LINE, "");
        }
        if (content.contains(RUBY_HASH)) {
            content = content.replaceAll("(?i)!ruby/.*\n", "\n");
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
    public static <T> T readToObject(File resource, Class<T> targetType) throws CustomException, IOException {
        if (targetType == null) {
            throw new UtilException("TargetType could not be null");
        }
        if (!FileUtil.isFile(resource)) {
            throw new UtilException("File not found");
        }
        if (!Validator.isMatchRegex(yaml_EXT_REG, FileTypeUtil.getType(resource))) {
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

    public static String writeyamlAsString(Object obj) throws JsonProcessingException {
        if (null == obj) {
            return null;
        }
        return mapper.writeValueAsString(obj);
    }

    public static void writeyaml(Object obj, File file) throws IOException {
        mapper.writeValue(file, obj);
    }

    public static void writeyaml(Object obj, String file) throws IOException {
        writeyaml(obj, FileUtil.newFile(file));
    }

}
