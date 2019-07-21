package com.ysc.device.service.controller;

import com.ysc.device.service.Application;
import com.ysc.device.service.aop.log.annotation.ControllerLogger;
import com.ysc.device.service.domain.request.SyncDataRequest;
import com.ysc.device.service.domain.response.BaseResponse;
import com.ysc.device.service.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/data")
public class CountApi {



    @ControllerLogger
    @Application.UserLoginToken
    @PostMapping("/syncStatistics")
    @ResponseBody
    public BaseResponse syncStatistics(SyncDataRequest request) {
        return null;
    }
}
