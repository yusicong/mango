package com.ysc.device.service.domain.request;

import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.validator.constraints.NotBlank;
/**
 * @author yusicong
 */
@Data
public class LoginByMobileRequest {

    @NotBlank
    private String mobile;

    @NotBlank
    private String password;

    public String getPasswordMD5(){
        return DigestUtils.md5Hex(this.password);
    }
}
