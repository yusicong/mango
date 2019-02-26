package com.ysc.device.service.domain.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserEntity extends BaseEntity{

    /**
     *用户唯一ID
     * */
    private Long userUuid;

    /**
     *用户手机号
     * */
    private String mobile;

    /**
     *用户密码
     * */
    private String password;

    /**
     *用户昵称
     * */
    private String nickName;

    /**
     *用户笔名
     * */
    private String penName;

    /**
     *个性签名
     * */
    private String signature;

    /**
     *头像
     * */
    private String imageUrl;

    /**
     *QQ号
     * */
    private String qq;

    /**
     *QQ第三方登录唯一号
     * */
    private String qqOpenid;

    /**
     *微信号
     * */
    private String wechat;

    /**
     *微信登录唯一号
     * */
    private String wechatOpenid;

    /**
     *用户email地址
     * */
    private String email;

    /**
     *用户真实姓名
     * */
    private String realName;

    /**
     *授权注册类型，手机号注册/微信号注册/QQ号注册 默认：wechat/qq/mobile
     * */
    private String authType;



    /**
     *用户注册IP地址
     * */
    private String regIp;

    /**
     *用户注册时间
     * */
    private String regTime;

    /**
     *用户账号状态，0默认，1冻结
     * */
    private Long status;

    /**
     *用户性别 0默认 1男 2女
     * */
    private Long sex;
}
