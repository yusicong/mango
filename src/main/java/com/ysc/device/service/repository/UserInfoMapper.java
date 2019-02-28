package com.ysc.device.service.repository;

import com.ysc.device.service.domain.entities.UserEntity;

/**
 * @author Administrator
 */
public interface UserInfoMapper {


    /**新增用户信息*/
    int insertUser(UserEntity userEntity);

    /**更新用户信息*/
    int updateUserByPhone(UserEntity userEntity);

    /**更新用户信息*/
    int updateUserByUuid(UserEntity userEntity);

    /**根据手机号查询密码*/
    String findPasswordByPhone(String mobile);

    /**根据手机号获得用户信息*/
    UserEntity findUserByPhone(String mobile);

    /**根据UUID获得用户信息*/
    UserEntity findUserById(String userUuid);

    /**根据qqOpenId获得用户信息*/
    UserEntity findUserByQqOpenId(String qqOpenId);

    /**根据wechatOpenId获得用户信息*/
    UserEntity findUserByWeChatOpenId(String wechatOpenId);


}