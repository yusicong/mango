package com.ysc.device.service.domain.request;

import lombok.Data;
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
}
