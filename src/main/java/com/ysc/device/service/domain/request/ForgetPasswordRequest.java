package com.ysc.device.service.domain.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author enmonster
 */
@Data
public class ForgetPasswordRequest {

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
     * 用户新密码
     */
    @NotBlank
    private String password;

    /**
     * 手机验证码
     */
    @NotNull
    private Integer code;

}
