package com.ysc.device.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IDEA
 *
 * @author : yusicng
 * @Date : 2018/7/23.
 * @Time : 15:13
 */
@Configuration
@ComponentScan
@MapperScan("com.ysc.device.service.repository")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
