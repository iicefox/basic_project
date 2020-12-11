package com.hxh.basic.project.util.resource;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.hxh.basic.project.exception.CustomException;
import com.hxh.basic.project.util.MethodUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class YmlUtil {
    private static final String YML_EXT_REG = "^ya?ml$";
    private static final String START_LINE = "---\n";
    public static final String RUBY_HASH = "!ruby/hash";

    private static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    /**
     * 私有化工具类 防止被实例化
     */
    private YmlUtil() {
    }

    /**
     * yml转Object
     *
     * @param content yml格式字符串内容
     * @return
     * @throws CustomException
     */
    public static Object toObject(String content) throws JsonProcessingException {
        if (StrUtil.isBlank(content)) {
            return null;
        }
        return toObject(content, Object.class);
    }

    public static <T> T toObject(String content, Class<T> valueType) throws JsonProcessingException {
        if (StrUtil.isBlank(content)) {
            return null;
        }
        if (content.startsWith(START_LINE)) {
            content = content.replace(START_LINE, "");
        }
        if (content.contains(RUBY_HASH)) {
            content = content.replaceAll("(?i)!ruby/.*\n", "\n");
        }
        return mapper.readValue(content, valueType);
    }

    public static Object readToObject(String resource) throws CustomException, IOException {
        if (StrUtil.isBlank(resource)) {
            return null;
        }
        return readToObject(FileUtil.newFile(FileUtil.getAbsolutePath(resource)));
    }

    public static Object readToObject(File resource) throws CustomException, IOException {
        if (!FileUtil.isFile(resource)) {
            throw new CustomException("文件不存在", MethodUtil.getLineInfo());
        }
        if (!Validator.isMatchRegex(YML_EXT_REG, FileTypeUtil.getType(resource))) {
            throw new CustomException("文件类型不是yaml/yml", MethodUtil.getLineInfo());
        }
        if (FileUtil.isEmpty(resource)) {
            return null;
        }
        return mapper.readValue(resource, Object.class);
    }

    /**
     * yml转Map
     *
     * @param document yml格式字符串
     * @return
     * @throws CustomException
     */
    public static Map<String, Object> readToMap(String document) throws CustomException, IOException {
        if (readToObject(document) instanceof Map) {
            return (Map<String, Object>) readToObject(document);
        }
        throw new CustomException("yml内容语法格式与Map不匹配", MethodUtil.getLineInfo());
    }

    public static Map<String, Object> readToMap(File document) throws CustomException, IOException {
        if (readToObject(document) instanceof Map) {
            return (Map<String, Object>) readToObject(document);
        }
        throw new CustomException("yml内容语法格式与Map不匹配", MethodUtil.getLineInfo());
    }

    /**
     * yml转List
     *
     * @param document yml格式字符串
     * @return
     * @throws CustomException
     */
    public static List<String> readToList(String document) throws CustomException, IOException {
        if (readToObject(document) instanceof List) {
            return (List<String>) readToObject(document);
        }
        throw new CustomException("yml内容语法格式与List不匹配", MethodUtil.getLineInfo());
    }

    public static List<String> readToList(File document) throws CustomException, IOException {
        if (readToObject(document) instanceof List) {
            return (List<String>) readToObject(document);
        }
        throw new CustomException("yml内容语法格式与List不匹配", MethodUtil.getLineInfo());
    }

    /**
     * yml转JSONObject
     *
     * @param document yml格式字符串
     * @return
     * @throws CustomException
     */
    public static JSONObject readToJson(String document) throws CustomException, IOException {
        if (JSONUtil.isJson(readToStr(document))) {
            return JSONUtil.parseObj(readToObject(document));
        }
        throw new CustomException("yml内容语法格式与JSON不匹配", MethodUtil.getLineInfo());
    }

    public static JSONObject readToJson(File document) throws CustomException, IOException {
        if (JSONUtil.isJson(readToStr(document))) {
            return JSONUtil.parseObj(readToObject(document));
        }
        throw new CustomException("yml内容语法格式与JSON不匹配", MethodUtil.getLineInfo());
    }

    /**
     * yml转JSON格式字符串
     *
     * @param document yml格式字符串
     * @return
     * @throws CustomException
     */
    public static String readToStr(String document) throws CustomException, IOException {
        return StrUtil.toString(readToObject(document));
    }

    public static String readToStr(File document) throws CustomException, IOException {
        return readToObject(document).toString();
    }

    public static String writeYmlAsString(Object obj) throws JsonProcessingException {
        if (null == obj) {
            return null;
        }
        return mapper.writeValueAsString(obj);
    }

    public static void writeYml(Object obj, File file) throws IOException {
        mapper.writeValue(file, obj);
    }

    public static void writeYml(Object obj, String file) throws IOException {
        writeYml(obj, FileUtil.newFile(file));
    }

}
