package com.ysc.device.service.domain.request;

import lombok.Data;

@Data
public class LoginByMboileRequest {

    private String mobile;

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
