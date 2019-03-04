package com.ysc.device.service.service;

import com.ysc.device.service.domain.request.RegisterRequest;
import com.ysc.device.service.domain.response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by IDEA
 *
 * @author : yusicng
 * @Date : 2018/7/23.
 * @Time : 17:54
 */
public interface FileService {

    /**通过手机号注册*/
    BaseResponse uploadAvatar(HttpServletRequest httpServletRequest);

    BaseResponse uploadFile(Map<String ,File> fileMap);

}
