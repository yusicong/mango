package com.ysc.device.service.repository;

import com.ysc.device.service.domain.entities.RegisterEntity;
import com.ysc.device.service.domain.entities.UserEntity;

/**
 * @author Administrator
 */
public interface UserInfoMapper {


    int insertUser(RegisterEntity registerEntity);

    String findPasswordByPhone(String mobile);

    UserEntity findUserByPhone(String mobile);

    UserEntity findUserById(String mobile);
}