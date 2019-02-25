package com.ysc.device.service.service;

import com.ysc.device.service.domain.entities.User;

/**
 * Created by IDEA
 *
 * @author : yusicng
 * @Date : 2018/7/23.
 * @Time : 17:54
 */
public interface TokenService {

     String getToken(User user);
}
