package com.ysc.device.service.controller;

import com.ysc.device.service.Application;
import com.ysc.device.service.aop.log.annotation.ControllerLogger;
import com.ysc.device.service.domain.response.BaseResponse;
import com.ysc.device.service.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author enmonster
 */
@RestController
@RequestMapping("api/file")
public class FileApi {

    @Autowired
    FileService fileService;

    @ControllerLogger
    @Application.UserLoginToken
    @PostMapping("/upload")
    @ResponseBody
    public BaseResponse upload(HttpServletRequest httpServletRequest) {
        return fileService.upload(httpServletRequest);
    }
}
