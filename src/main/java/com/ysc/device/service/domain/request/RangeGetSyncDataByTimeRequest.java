package com.ysc.device.service.domain.request;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 码字数据查询入参
 * */
public class RangeGetSyncDataByTimeRequest {

    /**
     * 用户id
     */
    private String userUuid;

    /**
     * 数据id
     */
    private String uuid;

    /**
     * 查询开始时间
     */
    private LocalDateTime beginTime;

    /**
     * 查询结束时间
     */
    private LocalDateTime endTime;

    private List<String> excludeUuidList;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public List<String> getExcludeUuidList() {
        return excludeUuidList;
    }

    public void setExcludeUuidList(List<String> excludeUuidList) {
        this.excludeUuidList = excludeUuidList;
    }
}
