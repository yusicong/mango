package com.ysc.device.service.repository;

import com.ysc.device.service.domain.entities.Users;

/**
 * @author Administrator
 */
public interface UsersMapper {
    int insert(Users record);

    int insertSelective(Users record);
}