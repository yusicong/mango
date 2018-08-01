package com.ysc.device.service.cache.impl;


import com.ysc.device.service.cache.CacheDao;
import com.ysc.device.service.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

@Component
public class RedisCacheDao implements CacheDao {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void setObject(String key, Object object) {
        setString(key, JsonUtils.toJSONString(object));
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz) {
        String value = getString(key);
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        return JsonUtils.toObject(value, clazz);
    }

    @Override
    public <K, V> HashMap<K, V> getHashMap(String key) {
        String value = getString(key);
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        return JsonUtils.toHashMap(value);
    }


    @Override
    public void setOneHashObj(String key, String mapKey, Object mapValue) {
        stringRedisTemplate.boundHashOps(key).put(mapKey, JsonUtils.toJSONString(mapValue));
    }

    @Override
    public <T> T getOneHashObj(String key, String mapKey, Class<T> clazz) {
        String value = (String) stringRedisTemplate.boundHashOps(key).get(mapKey);

        if (StringUtils.isEmpty(value)) {
            return null;
        }

        if(String.class.getName().equals(clazz.getName()))
        {
            return (T)value;
        }
        return JsonUtils.toObject(value, clazz);
    }

    @Override
    public <T> List<T> getAllHashObj(String key, Class<T> clazz) {
        List<Object> list = stringRedisTemplate.boundHashOps(key).values();

        if (list != null && list.size() != 0) {
            for(int i = 0; i < list.size(); i++) {
                list.set(i, JsonUtils.toObject(JsonUtils.toJSONString(list.get(i)), clazz));
            }
            return (List<T>) list;
        } else {
            return null;
        }
    }

    @Override
    public Boolean hasOneHashObjKey(String key, String mapKey) {
        return stringRedisTemplate.boundHashOps(key).hasKey(mapKey);
    }

}
