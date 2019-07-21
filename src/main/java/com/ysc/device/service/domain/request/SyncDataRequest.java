package com.ysc.device.service.domain.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Create by yusicong
 * Date 2019/7/17 14:48
 */
@Data
public class SyncDataRequest {
    /**
     * 用户唯一标识
     */
    private String userUuid;

    /**
     * 设备唯一标识
     */
    private String deviceUuid;

    /**
     * 上次同步时间
     */
    private LocalDateTime syncTime;

    /**
     * 所需更新数据体
     */
    private List<SyncDataRequestBody> data;
}
