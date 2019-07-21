package com.ysc.device.service.service.impl;

import com.ysc.device.service.domain.entities.SyncDataEntity;
import com.ysc.device.service.domain.request.SyncDataRequest;
import com.ysc.device.service.domain.request.SyncDataRequestBody;
import com.ysc.device.service.domain.response.BaseResponse;
import com.ysc.device.service.repository.SyncDataMapper;
import com.ysc.device.service.service.CountService;
import com.ysc.device.service.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Create by yusicong
 * Date 2019/7/17 15:19
 */
@Service
@Slf4j
public class CountServiceImpl implements CountService{

    @Autowired
    private SyncDataMapper syncDataMapper;
    @Override
    public BaseResponse syncStatistics(SyncDataRequest request) {
        for (SyncDataRequestBody syncDataRequestBody : request.getData()) {
            SyncDataEntity syncDataEntity = JsonUtils.toObject(JsonUtils.toJSONString(syncDataRequestBody), SyncDataEntity.class);
            syncDataEntity.setUserUuid(request.getUserUuid());
            syncDataEntity.setDeviceUuid(request.getDeviceUuid());
            syncDataMapper.InsertOrUpdateCodewordData(syncDataEntity);
        }
        LocalDateTime beginSyncTime = request.getSyncTime();
        LocalDateTime endSyncTime = LocalDateTime.now();

        return null;
    }
}
