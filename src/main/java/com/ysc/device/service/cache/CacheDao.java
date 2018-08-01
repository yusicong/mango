package com.ysc.device.service.cache;

import java.util.HashMap;
import java.util.List;

public interface CacheDao {
    /**
     * 通过键值获取值
     * @param key 键值
     * @return
     */
    String getString(String key);

    /**
     * 设置值
     * @param key 键
     * @param value 值
     */
    void setString(String key, String value);

    /**
     * 设置对象
     * @param key 键值
     */
    void setObject(String key, Object object);

    /**
     * 获取对象
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getObject(String key, Class<T> clazz);

    <K, V> HashMap<K, V> getHashMap(String key);

    /**
     * 设置hash中一个对象
     * @param key
     * @param mapKey
     * @param mapValue
     */
    void setOneHashObj(String key, String mapKey, Object mapValue);

    /**
     * 获取hash中一个对象
     * @param key
     * @param mapKey
     * @param <T>
     * @return
     */
    <T> T getOneHashObj(String key, String mapKey, Class<T> clazz);

    <T> List<T> getAllHashObj(String key, Class<T> clazz);

    Boolean hasOneHashObjKey(String key, String mapKey);
}
