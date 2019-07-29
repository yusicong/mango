package com.ysc.device.service.domain.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * null字段不显示
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserEntity extends BaseEntity {

    /**
     * 用户唯一ID
     */
    private String userUuid;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户笔名
     */
    private String penName;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 头像
     */
    private String imageUrl;

    /**
     * QQ号
     */
    private String qq;

    /**
     * QQ第三方登录唯一号
     */
    private String qqOpenId;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * 微信登录唯一号
     */
    private String wechatOpenId;

    /**
     * 用户email地址
     */
    private String email;

    /**
     * 用户真实姓名
     */
    private String realName;

    /**
     * 授权注册类型，手机号注册/微信号注册/QQ号注册 默认：wechat/qq/mobile
     */
    private Integer authType;


    /**
     * 用户注册IP地址
     */
    private String regIp;

    /**
     * 用户注册时间
     */
    private String regTime;

    /**
     * 用户账号状态，0默认，1冻结
     */
    private Integer status;

    /**
     * 用户性别 0默认 1男 2女
     */
    private Integer sex;

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPenName() {
        return penName;
    }

    public void setPenName(String penName) {
        this.penName = penName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }


    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getWechatOpenId() {
        return wechatOpenId;
    }

    public void setWechatOpenId(String wechatOpenId) {
        this.wechatOpenId = wechatOpenId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    /**
     * 用户手机号
     */
    private String zone;

    /**
     * 注册验证码
     */
    private Integer code;

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
