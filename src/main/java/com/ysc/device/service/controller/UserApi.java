package com.ysc.device.service.controller;

import com.ysc.device.service.Application;
import com.ysc.device.service.domain.entities.User;
import com.ysc.device.service.service.TokenService;
import com.ysc.device.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class UserApi {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    //登录
    @PostMapping("/login")
    public String login(@RequestBody User user){
        User userForBase=userService.findByUsername(user);
        if(userForBase==null){
            return "登录失败,用户不存在";
        }else {
            System.out.println("1111");
            if (!userForBase.getPassword().equals(user.getPassword())){
                return "登录失败,密码错误";
            }else {
                String token = tokenService.getToken(userForBase);
                return token;
            }
        }
    }
    @Application.UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
