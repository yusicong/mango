package com.ysc.device.service.domain.request;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Create by yusicong
 * Date 2019/7/17 14:48
 */
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

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public LocalDateTime getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(LocalDateTime syncTime) {
        this.syncTime = syncTime;
    }

    public List<SyncDataRequestBody> getData() {
        return data;
    }

    public void setData(List<SyncDataRequestBody> data) {
        this.data = data;
    }
}
