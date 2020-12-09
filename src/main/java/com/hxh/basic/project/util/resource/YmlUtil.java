package com.hxh.basic.project.util.resource;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.hxh.basic.project.exception.CustomException;
import com.hxh.basic.project.util.MethodUtil;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class YmlUtil {
    private static final String YML_EXT_REG = "^ya?ml$";

    /**
     * 私有化工具类 防止被实例化
     */
    private YmlUtil() {
    }

    /**
     * yml转Object
     *
     * @param resource yml格式字符串内容
     * @return
     * @throws CustomException
     */
    public static Object convertToObject(String resource) throws CustomException {
        if (FileUtil.isFile(resource)) {
            if (!Validator.isMatchRegex(YML_EXT_REG, FileTypeUtil.getTypeByPath(resource))) {
                throw new CustomException("非yml类型文件", MethodUtil.getLineInfo());
            }
            resource = ResourceUtil.readUtf8Str(resource);
        }
        if (StrUtil.isBlank(resource)) {
            return null;
        }
        if (resource.startsWith("---\n")) {
            resource = resource.replace("---\n", "");
        }
        if (resource.contains("!ruby/hash")) {
            resource = resource.replaceAll("(?i)!ruby/.*\n", "\n");
        }
        Yaml yaml = new Yaml();
        try {
            return yaml.load(resource);
        } catch (Exception e) {
            throw new CustomException("非yaml格式字符串", MethodUtil.getLineInfo());
        }
    }

    public static Object convertToObject(File resource) throws CustomException {
        if (!Validator.isMatchRegex(YML_EXT_REG, FileTypeUtil.getType(resource))) {
            throw new CustomException("非yml类型文件", MethodUtil.getLineInfo());
        }
        Yaml yaml = new Yaml();
        try {
            return yaml.load(FileUtil.getInputStream(resource));
        } catch (Exception e) {
            throw new CustomException("非yaml格式字符串", MethodUtil.getLineInfo());
        }
    }

    /**
     * yml转Map
     *
     * @param document yml格式字符串
     * @return
     * @throws CustomException
     */
    public static Map<String, Object> convertToMap(String document) throws CustomException {
        return (Map<String, Object>) convertToObject(document);
    }

    public static Map<String, Object> convertToMap(File document) throws CustomException {
        return (Map<String, Object>) convertToObject(document);
    }

    /**
     * yml转List
     *
     * @param document yml格式字符串
     * @return
     * @throws CustomException
     */
    public static List<String> toList(String document) throws CustomException {
        return (List<String>) convertToObject(document);
    }

    public static List<String> toList(File document) throws CustomException {
        return (List<String>) convertToObject(document);
    }

    /**
     * yml转JSONObject
     *
     * @param document yml格式字符串
     * @return
     * @throws CustomException
     */
    public static JSONObject convertToJson(String document) throws CustomException {
        return JSONUtil.parseObj(convertToMap(document));
    }

    public static JSONObject convertToJson(File document) throws CustomException {
        return JSONUtil.parseObj(convertToMap(document));
    }

    /**
     * yml转JSON格式字符串
     *
     * @param document yml格式字符串
     * @return
     * @throws CustomException
     */
    public static String convertToJsonStr(String document) throws CustomException {
        return JSONUtil.toJsonStr(convertToJson(document));
    }

    public static String convertToJsonStr(File document) throws CustomException {
        return JSONUtil.toJsonStr(convertToJson(document));
    }

//    public static String convertToYmlStr(Object obj) {
//        return null == obj ? null : obj instanceof JSON ? new Yaml().dump(obj) : new Yaml().dump(JSONUtil.parse(obj));
//    }

    public static String writeYmlAsString(Object obj) throws JsonProcessingException {
        if (null == obj) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.writeValueAsString(obj);
    }

    public static void writeYml(Object obj, File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(file, obj);
    }

    public static void writeYml(Object obj, String file) throws IOException {
        writeYml(obj, FileUtil.newFile(file));
    }

}
