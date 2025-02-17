package com.application.next.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象转换为JSON字符串
     * 
     * @param object 要转换的对象
     * @return JSON格式的字符串
     * @throws IOException 如果转换失败
     */
    public static String objectToJson(Object object) throws IOException {
        return objectMapper.writeValueAsString(object);
    }

    /**
     * 将JSON字符串转换为指定类型的对象
     * 
     * @param json JSON格式的字符串
     * @param clazz 要转换的目标类
     * @param <T> 目标对象的类型
     * @return 转换后的对象
     * @throws IOException 如果转换失败
     */
    public static <T> T jsonToObject(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    /**
     * 将JSON字符串转换为指定类型的List
     * 
     * @param json JSON格式的字符串
     * @param clazz List中元素的类型
     * @param <T> List中元素的类型
     * @return 转换后的List对象
     * @throws IOException 如果转换失败
     */
    public static <T> List<T> jsonToList(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    /**
     * 将JSON字符串转换为Map
     * 
     * @param json JSON格式的字符串
     * @return 转换后的Map对象
     * @throws IOException 如果转换失败
     */
    public static Map<String, Object> jsonToMap(String json) throws IOException {
        return objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});
    }

    /**
     * 将对象转换为Map
     * 
     * @param object 要转换的对象
     * @return 转换后的Map对象
     * @throws IOException 如果转换失败
     */
    public static Map<String, Object> objectToMap(Object object) throws IOException {
        return objectMapper.convertValue(object, new TypeReference<Map<String, Object>>() {});
    }

    /**
     * 将Map转换为指定类型的对象
     * 
     * @param map 包含对象数据的Map
     * @param clazz 要转换的目标类
     * @param <T> 目标对象的类型
     * @return 转换后的对象
     * @throws IOException 如果转换失败
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) throws IOException {
        return objectMapper.convertValue(map, clazz);
    }
}