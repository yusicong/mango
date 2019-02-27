package com.ysc.device.service.repository;

import com.ysc.device.service.domain.entities.RegisterEntity;
import com.ysc.device.service.domain.entities.UserEntity;

/**
 * @author Administrator
 */
public interface UserInfoMapper {


    int insertUser(UserEntity userEntity);

    String findPasswordByPhone(String mobile);

    UserEntity findUserByPhone(String mobile);

    UserEntity findUserById(String mobile);
}