package com.ysc.device.service.domain.request;

import com.ysc.device.service.domain.entities.base.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author yusicong
 */
@Data
public class RegisterRequest extends BaseEntity {

    /**
     * 授权注册类型，手机号注册/微信号注册/QQ号注册 默认：wechat/qq/mobile
     */
    @NotNull
    @Range(min = 1, max = 3)
    private int authType;

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

}
