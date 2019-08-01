package com.ysc.device.service.domain.entities;

import lombok.Data;

/**
 * Create by idea
 * Date 2019/7/17 14:50
 */
@Data
public class SyncDataEntity extends BaseEntity {
    /**
     * 本条数据唯一标识
     */
    private String uuid;

    /**
     * 更新数据内容
     */
    private String content;

    /**
     * 用户唯一标识
     */
    private String userUuid;

    /**
     * 设备唯一标识
     */
    private String deviceUuid;
}
