package com.ysc.device.service.service.impl;

import com.ysc.device.service.domain.entities.SyncDataEntity;
import com.ysc.device.service.domain.request.RangeGetSyncDataByTimeRequest;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Create by idea
 * @Date 2019/7/17 15:19
 * @author yusicong
 */
@Service
@Slf4j
public class CountServiceImpl implements CountService{

    @Autowired
    private SyncDataMapper syncDataMapper;
    @Override
    public BaseResponse syncStatistics(SyncDataRequest request) {
        /**存储此次变动数据的所有uuid 后面查询排除掉*/
        List<String> excludeUuidList = new ArrayList<>();
        /**遍历入参dataList*/
        for (SyncDataRequestBody syncDataRequestBody : request.getData()) {
            SyncDataEntity syncDataEntity = JsonUtils.toObject(JsonUtils.toJSONString(syncDataRequestBody), SyncDataEntity.class);
            excludeUuidList.add(syncDataRequestBody.getUuid());
            syncDataEntity.setUserUuid(request.getUserUuid());
            syncDataEntity.setDeviceUuid(request.getDeviceUuid());
            syncDataMapper.insertOrUpdateCodewordData(syncDataEntity);
        }
        LocalDateTime beginSyncTime = request.getSyncTime();
        LocalDateTime endSyncTime = LocalDateTime.now();
        String userUuid = request.getUserUuid();

        /**构造查询入参*/
        RangeGetSyncDataByTimeRequest rangeGetSyncDataByTimeRequest = new RangeGetSyncDataByTimeRequest();
        rangeGetSyncDataByTimeRequest.setBeginTime(beginSyncTime);
        rangeGetSyncDataByTimeRequest.setEndTime(endSyncTime);
        rangeGetSyncDataByTimeRequest.setUserUuid(userUuid);
        rangeGetSyncDataByTimeRequest.setExcludeUuidList(excludeUuidList);
        return BaseResponse.createSuccessResult(syncDataMapper.selectCodewordData(rangeGetSyncDataByTimeRequest));
    }
}
