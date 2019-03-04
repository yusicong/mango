package com.ysc.device.service.controller;

import com.ysc.device.service.Application;
import com.ysc.device.service.domain.request.*;
import com.ysc.device.service.domain.response.BaseResponse;
import com.ysc.device.service.service.FileService;
import com.ysc.device.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/file")
public class ImageApi {

    @Autowired
    FileService fileService;

    @Application.UserLoginToken
    @PostMapping("/uploadImage")
    public BaseResponse uploadImage(@RequestBody @Validated UpdateUserInfoRequest updateUserInfoRequest) {
//        return fileService.uploadImage(updateUserInfoRequest);
        return null;
    }
}
