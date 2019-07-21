package com.ysc.device.service.repository;

import com.ysc.device.service.domain.entities.SyncDataEntity;

/**
 * Create by yusicong
 * Date 2019/7/17 15:21
 */
public interface SyncDataMapper {
    /**
     * 插入或更新数据
     */
    int InsertOrUpdateCodewordData(SyncDataEntity request);

    /**
     * 查询数据
     */
    int SelectCodewordData(SyncDataEntity request);
}
