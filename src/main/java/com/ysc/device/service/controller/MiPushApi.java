package com.ysc.device.service.controller;

import com.ysc.device.service.aop.log.annotation.ControllerLogger;
import com.ysc.device.service.domain.response.BaseResponse;
import com.ysc.device.service.service.MiPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by idea
 * Date 2019/3/8 17:15
 * @author yusicong
 */

@RestController
@RequestMapping("api/miPush")
public class MiPushApi {

    @Autowired
    MiPushService miPushService;

    @ControllerLogger
    @GetMapping("/pushTest")
    public BaseResponse pushTest() {
        return miPushService.sendMessage();
    }
}
