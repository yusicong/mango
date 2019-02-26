package com.ysc.device.service.controller;

import com.ysc.device.service.Application;
import com.ysc.device.service.domain.entities.RegisterEntity;
import com.ysc.device.service.domain.entities.User;
import com.ysc.device.service.domain.entities.UserEntity;
import com.ysc.device.service.domain.request.LoginByMboileRequest;
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


    //登录
    @PostMapping("/loginByMboile")
    public BaseResponse login(@RequestBody @Validated LoginByMboileRequest request) {
        return userService.loginByMboile(request);
    }

    @PostMapping("/register")
    public BaseResponse register(@RequestBody @Validated RegisterEntity user) {
        return userService.register(user);
    }

    @Application.UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }
}
