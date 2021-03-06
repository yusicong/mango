package com.ysc.device.service.domain.request;

import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author yusicong
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

    public String buildMD5Password(){
        return DigestUtils.md5Hex(this.password);
    }

}
