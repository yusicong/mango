package com.ysc.device.service.repository;

import com.ysc.device.service.domain.entities.RegisterEntity;
import com.ysc.device.service.domain.entities.User;
import com.ysc.device.service.domain.entities.UserEntity;

/**
 * @author Administrator
 */
public interface UserInfoMapper {

    User findUserById(String Id);

    User findByUsername(String username);

    int insertUser(RegisterEntity registerEntity);

    String findPasswordByPhone(String mobile);

    UserEntity findUserByPhone(String mobile);
}