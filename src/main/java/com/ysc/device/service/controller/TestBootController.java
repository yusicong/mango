package com.ysc.device.service.controller;

import com.ysc.device.service.domain.entities.Users;
import com.ysc.device.service.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IDEA
 *
 * @author : yusicng
 * @Date : 2018/7/23.
 * @Time : 15:19
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/test")
public class TestBootController {

    @Autowired
    TestService testService;

    @RequestMapping("/sqlInsert")
    public Users insertOne() {
        Users users = new Users();
        users.setName("success");
        users.setPassword("123");
        users.setPhone("110");
        testService.addUser(users);
        return users;
    }
    @RequestMapping("/redisInsert")
    public Users redisInsert() {
        Users users = new Users();
        users.setName("success");
        users.setPassword("123");
        users.setPhone("110");
        testService.redisInsert(users);
        return users;
    }
}
