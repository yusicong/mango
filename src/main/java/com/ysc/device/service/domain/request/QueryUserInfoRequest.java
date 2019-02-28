package com.ysc.device.service.domain.request;

public class QueryUserInfoRequest{


    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户uuid
     */
    private String userUuid;

    /**
     * 用户qqid
     */
    private String qqOpenId;

    /**
     * 用户微信id
     */
    private String wechatOpenId;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    public String getWechatOpenId() {
        return wechatOpenId;
    }

    public void setWechatOpenId(String wechatOpenId) {
        this.wechatOpenId = wechatOpenId;
    }
}
