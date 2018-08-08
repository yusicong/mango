package com.ysc.device.service;

import com.ysc.device.service.socket.MultiSocketServer;
import com.ysc.device.service.socket.SocketServer;
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
//
//        MultiSocketServer multiSocketServer = new MultiSocketServer();
//        multiSocketServer.startSocketServer(8809);
    }
}
