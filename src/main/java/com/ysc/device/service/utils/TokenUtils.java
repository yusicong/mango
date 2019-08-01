package com.ysc.device.service.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ysc.device.service.domain.entities.UserEntity;
import org.apache.commons.lang.StringUtils;

/**
 * @author enmonster
 */
public class TokenUtils {

    public static String getToken(UserEntity user) {
        if (StringUtils.isBlank(user.getPassword())){
            user.setPassword("a96500909");
        }
        String token="";
        token= JWT.create().withAudience(user.getUserUuid()+"")
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
