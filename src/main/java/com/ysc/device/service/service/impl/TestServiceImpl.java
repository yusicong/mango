package com.ysc.device.service.service.impl;

import com.ysc.device.service.cache.CacheDao;
import com.ysc.device.service.domain.entities.Users;
import com.ysc.device.service.domain.enums.RedisKeyPrefixEnum;
import com.ysc.device.service.repository.UsersMapper;
import com.ysc.device.service.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IDEA
 *
 * @author : yusicng
 * @Date : 2018/7/23.
 * @Time : 17:56
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    UsersMapper usersMapper;

    @Autowired
    CacheDao cacheDao;

    @Override
    public int addUser(Users user) {
        return usersMapper.insert(user);
    }

    @Override
    public int redisInsert(Users user) {
        cacheDao.setObject(RedisKeyPrefixEnum.DEVICE_1.getValue(),user);
        return 0;
    }
}
