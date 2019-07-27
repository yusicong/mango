package com.ysc.device.service.domain.request;

/**
 * Create by yusicong
 * Date 2019/7/17 14:50
 */
public class SyncDataRequestBody{
    /**
     * 本条数据唯一标识
     */
    private String uuid;

    /**
     * 更新数据内容
     */
    private String content;

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
}
