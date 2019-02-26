package com.ysc.device.service.service;

import com.ysc.device.service.domain.entities.RegisterEntity;
import com.ysc.device.service.domain.entities.User;
import com.ysc.device.service.domain.response.BaseResponse;

/**
 * Created by IDEA
 *
 * @author : yusicng
 * @Date : 2018/7/23.
 * @Time : 17:54
 */
public interface UserService {

    User findUserById(String Id);

    User findByUsername(User username);

    BaseResponse register(RegisterEntity registerEntity);

}
