package com.ysc.device.service.domain.entities;

public class BaseEntity {

    /**
     *创建时间
     * */
    private String createTime;

    /**
     *更新时间
     * */
    private String updateTime;

    /**
     *token
     * */
    private String token;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
