package com.ysc.device.service.service;

import com.ysc.device.service.domain.request.SyncDataRequest;
import com.ysc.device.service.domain.response.BaseResponse;

/**
 * Create by idea
 * Date 2019/7/17 15:18
 * @author yusicong
 */
public interface CountService {

    BaseResponse syncStatistics(SyncDataRequest request);
}
