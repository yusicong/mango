package com.ysc.device.service.controller;

import com.ysc.device.service.Application;
import com.ysc.device.service.aop.log.annotation.ControllerLogger;
import com.ysc.device.service.domain.request.SyncDataRequest;
import com.ysc.device.service.domain.response.BaseResponse;
import com.ysc.device.service.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yusicong
 */
@RestController
@RequestMapping("api/data")
public class CountApi {


    @Autowired
    private CountService countService;

    @ControllerLogger
    @Application.UserLoginToken
    @PostMapping("/syncStatistics")
    @ResponseBody
    public BaseResponse syncStatistics(@Validated @RequestBody SyncDataRequest request) {
        return countService.syncStatistics(request);
    }
}
