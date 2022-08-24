package com.knight.gatewaycommon.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * JSON 工具类
 *
 * @author TortoiseKnightB
 * @date 2022/02/10
 */
public class JsonHelper {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        // PS:向 Jackson 核心注册序列化 java.time 对象的能力
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        // Date类格式化（与数据库交接），LocalDate等（与前端交接）由 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") 进行格式化
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // PS:关闭功能 ———— 遇到未知字段属性时抛异常
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String toJSON(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            // PS:抛出运行时异常不用处理，系统会自动向外抛出，最终由自定义的全局异常aop处理
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
