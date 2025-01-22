package com.application.next.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转换为JSON字符串
     */
    public static String objectToJson(Object object) throws IOException {
        return objectMapper.writeValueAsString(object);
    }

    /**
     * JSON字符串转换为对象
     */
    public static <T> T jsonToObject(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    /**
     * JSON字符串转换为List对象
     */
    public static <T> List<T> jsonToList(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    /**
     * JSON字符串转换为Map
     */
    public static Map<String, Object> jsonToMap(String json) throws IOException {
        return objectMapper.readValue(json, new TypeReference<>() {
        });
    }

    /**
     * 对象转换为Map
     */
    public static Map<String, Object> objectToMap(Object object) throws IOException {
        return objectMapper.convertValue(object, Map.class);
    }

    /**
     * Map转换为对象
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) throws IOException {
        return objectMapper.convertValue(map, clazz);
    }
}