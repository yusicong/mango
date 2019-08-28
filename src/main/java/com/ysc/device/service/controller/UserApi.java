package com.ysc.device.service.controller;

import com.ysc.device.service.Application;
import com.ysc.device.service.aop.log.annotation.ControllerLogger;
import com.ysc.device.service.domain.request.*;
import com.ysc.device.service.domain.response.BaseResponse;
import com.ysc.device.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserApi {

    @Autowired
    UserService userService;

    /**
     * 手机登录
     */
    @ControllerLogger
    @PostMapping("/loginByMobile")
    public BaseResponse login(@RequestBody @Validated LoginByMobileRequest request) {
        return userService.loginByMobile(request);
    }

    /**
     * 第三方登陆
     */
    @ControllerLogger
    @PostMapping("/loginByOther")
    public BaseResponse loginByOther(@RequestBody @Validated LoginByOtherRequest request) {
        return userService.loginByOther(request);
    }

    /**
     * 手机号注册
     */
    @ControllerLogger
    @PostMapping("/register")
    public BaseResponse register(@RequestBody @Validated RegisterRequest user) {
        return userService.register(user);
    }

    /**
     * 忘记密码
     */
    @ControllerLogger
    @PostMapping("/forgetPassword")
    public BaseResponse forgetPassword(@RequestBody @Validated ForgetPasswordRequest forgetPasswordRequest) {
        return userService.forgetPassword(forgetPasswordRequest);
    }

    @ControllerLogger
    @Application.UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }

    /**
     * 获取用户信息
     */
    @ControllerLogger
    @Application.UserLoginToken
    @PostMapping("/getUserInfo")
    public BaseResponse getUserInfo(@RequestBody @Validated QueryUserInfoRequest queryUserInfoRequest) {
        return userService.getUserInfo(queryUserInfoRequest);
    }

    /**
     * 修改用户信息
     */
    @ControllerLogger
    @Application.UserLoginToken
    @PostMapping("/modifyUserInfo")
    public BaseResponse modifyUserInfo(@RequestBody @Validated UpdateUserInfoRequest updateUserInfoRequest) {
        return userService.modifyUserInfo(updateUserInfoRequest);
    }
}
