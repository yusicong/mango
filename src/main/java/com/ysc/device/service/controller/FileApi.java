package com.ysc.device.service.controller;

import com.ysc.device.service.aop.log.annotation.ControllerLogger;
import com.ysc.device.service.domain.response.BaseResponse;
import com.ysc.device.service.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yusicong
 */
@RestController
@RequestMapping("api/file")
public class FileApi {

    @Autowired
    FileService fileService;

    @ControllerLogger
//    @Application.UserLoginToken
    @PostMapping("/upload")
    @ResponseBody
    public BaseResponse upload(HttpServletRequest httpServletRequest) {
        return fileService.upload(httpServletRequest);
    }
}
