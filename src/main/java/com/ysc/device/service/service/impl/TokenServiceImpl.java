package com.ysc.device.service.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ysc.device.service.domain.entities.User;
import com.ysc.device.service.service.TokenService;
import com.ysc.device.service.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by IDEA
 *
 * @author : yusicng
 * @Date : 2018/7/23.
 * @Time : 17:56
 */
@Service
public class TokenServiceImpl implements TokenService
{
    @Override
    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getId())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
