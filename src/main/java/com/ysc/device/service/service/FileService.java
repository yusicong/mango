package com.ysc.device.service.service;

import com.ysc.device.service.domain.request.RegisterRequest;
import com.ysc.device.service.domain.response.BaseResponse;

/**
 * Created by IDEA
 *
 * @author : yusicng
 * @Date : 2018/7/23.
 * @Time : 17:54
 */
public interface FileService {

    /**通过手机号注册*/
    BaseResponse uploadImage(RegisterRequest registerRequest);

}
