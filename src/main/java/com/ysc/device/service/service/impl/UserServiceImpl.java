package com.ysc.device.service.service.impl;

import com.ysc.device.service.cache.CacheDao;
import com.ysc.device.service.domain.entities.User;
import com.ysc.device.service.domain.entities.Users;
import com.ysc.device.service.domain.enums.RedisKeyPrefixEnum;
import com.ysc.device.service.repository.UserInfoMapper;
import com.ysc.device.service.repository.UsersMapper;
import com.ysc.device.service.service.TestService;
import com.ysc.device.service.service.UserService;
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
public class UserServiceImpl implements UserService
{

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public User findUserById(String Id) {
        return userInfoMapper.findUserById(Id);
    }

    @Override
    public User findByUsername(User user) {
        return userInfoMapper.findByUsername(user.getUsername());
    }
}
