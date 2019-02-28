package com.ysc.device.service.domain.dto;

import com.ysc.device.service.domain.entities.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class RegisterDTO extends BaseEntity {

    /**
     * 授权注册类型，手机号注册/微信号注册/QQ号注册 默认：wechat/qq/mobile
     */
    @NotBlank
    private String authType;

    /**
     * 用户手机号-区号
     */
    @NotNull
    private Integer zone;

    /**
     * 用户手机号
     */
    @NotBlank
    private String mobile;

    /**
     * 用户密码
     */
    @NotBlank
    private String password;

    /**
     * 注册验证码
     */
    @NotNull
    private Integer code;


    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
