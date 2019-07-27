package com.ysc.device.service.domain.entities;

/**
 * Create by yusicong
 * Date 2019/7/17 14:50
 */

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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
}
