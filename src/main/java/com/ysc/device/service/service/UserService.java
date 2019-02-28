package com.ysc.device.service.service;

import com.ysc.device.service.domain.dto.ForgetPasswordDTO;
import com.ysc.device.service.domain.dto.RegisterDTO;
import com.ysc.device.service.domain.entities.UserEntity;
import com.ysc.device.service.domain.request.LoginByMobileRequest;
import com.ysc.device.service.domain.request.LoginByOtherRequest;
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
    BaseResponse register(RegisterDTO registerDTO);

    /**通过手机号登陆*/
    BaseResponse loginByMobile(LoginByMobileRequest request);

    /**通过第三方登陆*/
    BaseResponse loginByOther(LoginByOtherRequest request);

    /**通过uuid查询user信息*/
    UserEntity findUserById(String userUuid);

    /**手机号忘记密码*/
    BaseResponse forgetPassword(ForgetPasswordDTO forgetPasswordDTO);


}
