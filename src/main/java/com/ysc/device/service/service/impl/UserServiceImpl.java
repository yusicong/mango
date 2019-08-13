package com.ysc.device.service.service.impl;

import com.ysc.device.service.domain.entities.BaseEntity;
import com.ysc.device.service.domain.entities.UserEntity;
import com.ysc.device.service.domain.enums.AuthTypeEnum;
import com.ysc.device.service.domain.enums.BaseErrorCodeEnum;
import com.ysc.device.service.domain.request.*;
import com.ysc.device.service.domain.response.BaseResponse;
import com.ysc.device.service.repository.UserInfoMapper;
import com.ysc.device.service.service.UserService;
import com.ysc.device.service.service.impl.abs.AbsServiceImpl;
import com.ysc.device.service.utils.JsonUtils;
import com.ysc.device.service.utils.TokenUtils;
import com.ysc.device.service.utils.UserUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IDEA
 *
 * @author : yusicong
 * @Date : 2018/7/23.
 * @Time : 17:56
 */
@Service
public class UserServiceImpl extends AbsServiceImpl implements UserService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public BaseResponse register(RegisterRequest registerRequest) {
        BaseResponse baseResponse = new BaseResponse();
        if (!StringUtils.isBlank(userInfoMapper.findPasswordByPhone(registerRequest.getMobile()))) {
            baseResponse.setSuccess(false);
            baseResponse.setErrorCode(BaseErrorCodeEnum.REGISTER_STATUS_2.getValue() + "");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.REGISTER_STATUS_2.getText());
            return baseResponse;
        }
        /**验证码校验*/
        BaseResponse verificationCodeCheckResponse = verificationCodeCheck(registerRequest.getCode(), registerRequest.getMobile(), registerRequest.getZone());
        if (!verificationCodeCheckResponse.isSuccess()) {
            return verificationCodeCheckResponse;
        }
        UserEntity userEntity = JsonUtils.toObject(JsonUtils.toJSONString(registerRequest), UserEntity.class);
        userEntity.setUserUuid(UserUtils.getRandomUuid());
        userEntity.setNickName(UserUtils.getRandomName());
        if (1 != userInfoMapper.insertUser(userEntity)) {
            baseResponse.setSuccess(false);
            baseResponse.setErrorCode(BaseErrorCodeEnum.REGISTER_STATUS_2.getValue() + "");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.REGISTER_STATUS_2.getText());
            return baseResponse;
        }
        baseResponse.setSuccess(true);
        baseResponse.setErrorCode(BaseErrorCodeEnum.REGISTER_STATUS_1.getValue() + "");
        baseResponse.setErrorMessage(BaseErrorCodeEnum.REGISTER_STATUS_1.getText());
        return baseResponse;
    }

    @Override
    public BaseResponse loginByMobile(LoginByMobileRequest request) {
        BaseResponse<UserEntity> baseResponse = new BaseResponse<>();
        UserEntity userEntity = userInfoMapper.findUserByPhone(request.getMobile());
        /**判断用户是否存在*/
        BaseResponse userIsExistByMobileResponse = userIsExistByMobile(userEntity);
        if (!userIsExistByMobileResponse.isSuccess()) {
            return userIsExistByMobileResponse;
        }

        /**判断密码是否正确 正确*/
        if (request.getPassword().equals(userEntity.getPassword())) {
            userEntity.setToken(TokenUtils.getToken(userEntity));
            userEntity.setPassword(null);
            baseResponse.setSuccess(true);
            baseResponse.setData(userEntity);
            baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_1.getValue() + "");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.LOGIN_STATUS_1.getText());
            return baseResponse;
        }
        /**错误*/
        else {
            baseResponse.setSuccess(false);
            baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_3.getValue() + "");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.LOGIN_STATUS_3.getText());
            return baseResponse;
        }
    }

    @Override
    public BaseResponse loginByOther(LoginByOtherRequest request) {
        BaseResponse<BaseEntity> baseResponse = new BaseResponse<BaseEntity>();
        /**qq登陆*/
        if (AuthTypeEnum.AUTH_TYPE_ENUM_2.getValue().equals(request.getAuthType())) {
            /**用户不存在 自动注册*/
            UserEntity user = userInfoMapper.findUserByQqOpenId(request.getOpenid());
            if (null == user) {
                UserEntity userEntity = new UserEntity();
                userEntity.setAuthType(request.getAuthType());
                userEntity.setUserUuid(UserUtils.getRandomUuid());
                userEntity.setQqOpenId(request.getOpenid());
                userEntity.setImageUrl(request.getImageUrl());
                userEntity.setNickName((null != request.getNickName()) ? request.getNickName() : UserUtils.getRandomName());
                userEntity.setSex(request.getSex());
                /**注册成功*/
                if (1 == userInfoMapper.insertUser(userEntity)) {
                    /**生成token*/
                    userEntity.setToken(TokenUtils.getToken(userEntity));
                    baseResponse.setSuccess(true);
                    baseResponse.setData(userEntity);
                    baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_1.getValue() + "");
                    baseResponse.setErrorMessage(BaseErrorCodeEnum.LOGIN_STATUS_1.getText());
                    return baseResponse;
                } else {
                    baseResponse.setSuccess(false);
                    baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_5.getValue() + "");
                    baseResponse.setErrorMessage(BaseErrorCodeEnum.LOGIN_STATUS_5.getText());
                    return baseResponse;
                }
            }
            /**用户存在正常登陆*/
            else {
                user.setToken(TokenUtils.getToken(user));
                baseResponse.setSuccess(true);
                baseResponse.setData(user);
                baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_1.getValue() + "");
                baseResponse.setErrorMessage(BaseErrorCodeEnum.LOGIN_STATUS_1.getText());
                return baseResponse;
            }
        }

        /**微信登陆*/
        if (AuthTypeEnum.AUTH_TYPE_ENUM_3.getValue().equals(request.getAuthType())) {
            /**用户不存在 自动注册*/
            UserEntity user = userInfoMapper.findUserByWeChatOpenId(request.getOpenid());
            if (null == user) {
                UserEntity userEntity = new UserEntity();
                userEntity.setAuthType(request.getAuthType());
                userEntity.setUserUuid(UserUtils.getRandomUuid());
                userEntity.setWechatOpenId(request.getOpenid());
                userEntity.setImageUrl(request.getImageUrl());
                userEntity.setNickName((null != request.getNickName()) ? request.getNickName() : UserUtils.getRandomName());
                userEntity.setSex(request.getSex());
                /**注册成功*/
                if (1 == userInfoMapper.insertUser(userEntity)) {
                    BaseEntity baseEntity = new BaseEntity();
                    /**生成token*/
                    baseEntity.setToken(TokenUtils.getToken(userEntity));
                    baseResponse.setSuccess(true);
                    baseResponse.setData(baseEntity);
                    baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_1.getValue() + "");
                    baseResponse.setErrorMessage(BaseErrorCodeEnum.LOGIN_STATUS_1.getText());
                    return baseResponse;
                } else {
                    baseResponse.setSuccess(false);
                    baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_5.getValue() + "");
                    baseResponse.setErrorMessage(BaseErrorCodeEnum.LOGIN_STATUS_5.getText());
                    return baseResponse;
                }
            }
            /**用户存在正常登陆*/
            else {
                System.out.println("用户存在");
                BaseEntity baseEntity = new BaseEntity();
                /**生成token*/
                baseEntity.setToken(TokenUtils.getToken(user));
                baseResponse.setSuccess(true);
                baseResponse.setData(baseEntity);
                baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_1.getValue() + "");
                baseResponse.setErrorMessage(BaseErrorCodeEnum.LOGIN_STATUS_1.getText());
                return baseResponse;
            }
        }
        return null;
    }

    @Override
    public UserEntity findUserById(String userUuid) {
        return userInfoMapper.findUserById(userUuid);
    }

    @Override
    public BaseResponse forgetPassword(ForgetPasswordRequest forgetPasswordRequest) {
        UserEntity userEntity = userInfoMapper.findUserByPhone(forgetPasswordRequest.getMobile());
        /**判断用户是否存在*/
        BaseResponse userIsExistByMobileResponse = userIsExistByMobile(userEntity);
        if (!userIsExistByMobileResponse.isSuccess()) {
            return userIsExistByMobileResponse;
        }
        BaseResponse baseResponse = new BaseResponse();
        /**验证码校验*/
        BaseResponse verificationCodeCheckResponse = verificationCodeCheck(forgetPasswordRequest.getCode(), forgetPasswordRequest.getMobile(), forgetPasswordRequest.getZone());
        if (!verificationCodeCheckResponse.isSuccess()) {
            return verificationCodeCheckResponse;
        }
        /**新密码与旧密码相同*/
        if (forgetPasswordRequest.getPassword().equals(userEntity.getPassword())) {
            baseResponse.setSuccess(false);
            baseResponse.setErrorCode(BaseErrorCodeEnum.UPDATE_STATUS_1.getValue() + "");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.UPDATE_STATUS_1.getText());
            return baseResponse;
        }
        userEntity.setPassword(forgetPasswordRequest.getPassword());
        if (1 == userInfoMapper.updateUserByPhone(userEntity)) {
            baseResponse.setSuccess(true);
            baseResponse.setErrorCode(BaseErrorCodeEnum.UPDATE_STATUS_2.getValue() + "");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.UPDATE_STATUS_2.getText());
            return baseResponse;
        } else {
            baseResponse.setSuccess(false);
            baseResponse.setErrorCode(BaseErrorCodeEnum.UPDATE_STATUS_3.getValue() + "");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.UPDATE_STATUS_3.getText());
            return baseResponse;
        }
    }

    @Override
    public BaseResponse getUserInfo(QueryUserInfoRequest queryUserInfoRequest) {
        BaseResponse baseResponse = new BaseResponse();
        UserEntity userEntity = new UserEntity();
        if (!StringUtils.isBlank(queryUserInfoRequest.getMobile())) {
            userEntity = userInfoMapper.findUserByPhone(queryUserInfoRequest.getMobile());
            if (null != userEntity) {
                return BaseResponse.createSuccessResult(userEntity);
            }
        }
        if (!StringUtils.isBlank(queryUserInfoRequest.getUserUuid())) {
            userEntity = userInfoMapper.findUserById(queryUserInfoRequest.getUserUuid());
            if (null != userEntity) {
                return BaseResponse.createSuccessResult(userEntity);
            }
        }
        if (!StringUtils.isBlank(queryUserInfoRequest.getQqOpenId())) {
            userEntity = userInfoMapper.findUserByQqOpenId(queryUserInfoRequest.getQqOpenId());
            if (null != userEntity) {
                return BaseResponse.createSuccessResult(userEntity);
            }
        }
        if (!StringUtils.isBlank(queryUserInfoRequest.getWechatOpenId())) {
            userEntity = userInfoMapper.findUserByWeChatOpenId(queryUserInfoRequest.getWechatOpenId());
            if (null != userEntity) {
                return BaseResponse.createSuccessResult(userEntity);
            }
        }
        baseResponse.setSuccess(false);
        baseResponse.setErrorCode(BaseErrorCodeEnum.UPDATE_STATUS_3.getValue() + "");
        baseResponse.setErrorMessage(BaseErrorCodeEnum.UPDATE_STATUS_3.getText());
        return baseResponse;
    }

    @Override
    public BaseResponse modifyUserinfo(UpdateUserInfoRequest updateUserInfoRequest) {
        BaseResponse<UserEntity> baseResponse = new BaseResponse<>();
        UserEntity userEntity = JsonUtils.toObject(JsonUtils.toJSONString(updateUserInfoRequest), UserEntity.class);

        BaseResponse isDiff = userinfoIsDifferent(userEntity);
        if (!isDiff.isSuccess()) {
            return isDiff;
        }
        if (1 == userInfoMapper.updateUserByUuid(userEntity)) {
            baseResponse.setSuccess(true);
            UserEntity user = userInfoMapper.findUserById(userEntity.getUserUuid());
            user.setPassword(null);
            baseResponse.setData(user);
            baseResponse.setErrorCode(BaseErrorCodeEnum.UPDATE_STATUS_2.getValue() + "");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.UPDATE_STATUS_2.getText());
            return baseResponse;
        }
        baseResponse.setSuccess(false);
        baseResponse.setErrorCode(BaseErrorCodeEnum.UPDATE_STATUS_3.getValue() + "");
        baseResponse.setErrorMessage(BaseErrorCodeEnum.UPDATE_STATUS_3.getText());
        return baseResponse;
    }
}
