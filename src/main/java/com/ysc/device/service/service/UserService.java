package com.ysc.device.service.service;

import com.ysc.device.service.domain.entities.RegisterEntity;
import com.ysc.device.service.domain.entities.UserEntity;
import com.ysc.device.service.domain.request.LoginByMobileRequest;
import com.ysc.device.service.domain.response.BaseResponse;

/**
 * Created by IDEA
 *
 * @author : yusicng
 * @Date : 2018/7/23.
 * @Time : 17:54
 */
public interface UserService {


    BaseResponse register(RegisterEntity registerEntity);

    BaseResponse loginByMobile(LoginByMobileRequest request);

    /**通过uuid查询user信息*/
    UserEntity findUserById(String userUuid);

}
