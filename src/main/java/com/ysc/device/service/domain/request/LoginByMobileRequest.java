package com.ysc.device.service.domain.request;

import org.hibernate.validator.constraints.NotBlank;

public class LoginByMobileRequest {

    @NotBlank
    private String mobile;

    @NotBlank
    private String password;

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
}
