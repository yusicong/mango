package com.ysc.device.service.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * JSON工具类
 * @author Administrator
 */
public class JsonUtils {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);


    private static String writeValue(Object object) {
        if (object == null) {
            return null;
        } else if(object instanceof String) {
            return (String) object;
        }

        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("writeJsonValue error, ", e);
        }
        return null;
    }

    private static <T> T readValue(String json, Class<T> t) {
        if (json == null) {
            return null;
        }

        try {
            return OBJECT_MAPPER.readValue(json, t);
        } catch (Exception e) {
            LOGGER.error("readJsonValue error, ", e);
        }
        return null;
    }

    private static <T> T readValue(String json, TypeReference<T> t) {
        if (json == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(json, t);
        } catch (Exception e) {
            LOGGER.error("readJsonValue error, ", e);
        }
        return null;
    }


    /**
     * java对象转换为json字符串
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        return writeValue(object);
    }


    /**
     * json转换为java对象
     *
     * @param json json
     * @param t    对象类型
     * @param <T>
     * @return
     */
    public static <T> T toObject(String json, Class<T> t) {
        return readValue(json, t);
    }


    /**
     * json转换为List对象
     *
     * @param json json
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String json) {
        return readValue(json, new TypeReference<List<T>>() {
        });
    }

    /**
     * json转换为List对象
     *
     */

    public static <T> List<T> toList(String jsonStr, Class<?> clazz) {
        List<T> list = new ArrayList<T>();
        try {
            TypeFactory t = TypeFactory.defaultInstance();
            list = OBJECT_MAPPER.readValue(jsonStr,
                    t.constructCollectionType(ArrayList.class, clazz));
        } catch (IOException e) {
            LOGGER.error("readJsonValue error, ", e);
        }
        return list;
    }


    /**
     * json转换为Map对象
     *
     * @param json json
     * @return
     */
    public static <K, V> Map<K, V> toMap(String json) {
        return readValue(json, new TypeReference<Map<K, V>>() {
        });
    }

    /**
     * json转换为HashMap对象
     *
     * @param json json
     * @return
     */
    public static <K, V> HashMap<K, V> toHashMap(String json) {
        return readValue(json, new TypeReference<HashMap<K, V>>() {
        });
    }



    /**
     * json转换为java对象
     *
     * @param json json
     * @param t    对象类型
     * @param <T>
     * @return
     */
    public static <T> T toObject(String json, TypeReference<T> t) {
        return readValue(json, t);
    }

}
