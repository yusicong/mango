package com.ysc.device.service.domain.request;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * null字段不显示
 * @author yusicong
 */
@Data
public class UpdateUserInfoRequest {

    /**
     * 用户唯一ID
     */
    @NotBlank
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
     * 微信号
     */
    private String wechat;

    /**
     * 用户email地址
     */
    @Email
    private String email;

    /**
     * 用户真实姓名
     */
    private String realName;

    /**
     * 用户性别 0默认 1男 2女
     */
    private Integer sex;
    /**
     * 用户手机号
     */
    private String zone;

    /**
     * 注册验证码
     */
    private Integer code;
}
