package com.ysc.device.service.domain.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
/**
 * @author enmonster
 */
@Data
public class LoginByOtherRequest {

    @NotNull
    private Integer authType;

    @NotBlank
    private String openid;

    private String imageUrl;

    private String nickName;

    private Integer sex;
}
