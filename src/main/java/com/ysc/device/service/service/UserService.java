package com.ysc.device.service.service;

import com.ysc.device.service.domain.request.*;
import com.ysc.device.service.domain.entities.UserEntity;
import com.ysc.device.service.domain.response.BaseResponse;

/**
 * Created by IDEA
 *
 * @author : yusicng
 * @Date : 2018/7/23.
 * @Time : 17:54
 */
public interface UserService {

    /**通过手机号注册*/
    BaseResponse register(RegisterRequest registerRequest);

    /**通过手机号登陆*/
    BaseResponse loginByMobile(LoginByMobileRequest request);

    /**通过第三方登陆*/
    BaseResponse loginByOther(LoginByOtherRequest request);

    /**通过uuid查询user信息*/
    UserEntity findUserById(String userUuid);

    /**手机号忘记密码*/
    BaseResponse forgetPassword(ForgetPasswordRequest forgetPasswordRequest);

    /**获取用户信息*/
    BaseResponse getUserInfo(QueryUserInfoRequest queryUserInfoRequest);


}
