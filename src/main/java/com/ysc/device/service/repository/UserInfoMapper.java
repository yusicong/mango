package com.ysc.device.service.repository;

import com.ysc.device.service.domain.entities.User;

/**
 * @author Administrator
 */
public interface UserInfoMapper {

    User findUserById(String Id);

    User findByUsername(String username);

}