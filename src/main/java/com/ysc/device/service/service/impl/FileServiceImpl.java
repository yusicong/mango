package com.ysc.device.service.service.impl;

import com.ysc.device.service.domain.request.RegisterRequest;
import com.ysc.device.service.domain.response.BaseResponse;
import com.ysc.device.service.service.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService{
    @Override
    public BaseResponse uploadImage(RegisterRequest registerRequest) {
        return null;
    }
}
