package com.ysc.device.service.repository;

import com.ysc.device.service.domain.entities.SyncDataEntity;
import com.ysc.device.service.domain.request.RangeGetSyncDataByTimeRequest;

import java.util.List;

/**
 * Create by yusicong
 * Date 2019/7/17 15:21
 * @author enmonster
 */
public interface SyncDataMapper {
    /**
     * 插入或更新数据
     */
    int insertOrUpdateCodewordData(SyncDataEntity request);

    /**
     * 查询数据
     */
    List<SyncDataEntity> selectCodewordData(RangeGetSyncDataByTimeRequest request);
}
