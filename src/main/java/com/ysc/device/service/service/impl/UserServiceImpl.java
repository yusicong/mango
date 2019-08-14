package com.ysc.device.service.service.impl;

import com.ysc.device.service.domain.entities.UserEntity;
import com.ysc.device.service.domain.enumes.AuthTypeEnum;
import com.ysc.device.service.domain.enumes.BaseErrorCodeEnum;
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
        if (!StringUtils.isBlank(userInfoMapper.findPasswordByPhone(registerRequest.getMobile()))) {
            return BaseResponse.createFailResult(BaseErrorCodeEnum.REGISTER_STATUS_2);
        }
        /**验证码校验*/
        BaseResponse verificationCodeCheckResponse = verificationCodeCheck(registerRequest.getCode(), registerRequest.getMobile(), registerRequest.getZone());
        if (!verificationCodeCheckResponse.isSuccess()) {
            return verificationCodeCheckResponse;
        }
        UserEntity userEntity = JsonUtils.toObject(JsonUtils.toJSONString(registerRequest), UserEntity.class);
        userEntity.setUserUuid(UserUtils.getRandomUuid());
        userEntity.setNickName(UserUtils.getRandomName());
        userEntity.setPassword(userEntity.getPasswordMD5());
        if (1 != userInfoMapper.insertUser(userEntity)) {
            return BaseResponse.createFailResult(BaseErrorCodeEnum.REGISTER_STATUS_2);
        }
        return BaseResponse.createSuccessResult(null,BaseErrorCodeEnum.REGISTER_STATUS_1);
    }

    @Override
    public BaseResponse loginByMobile(LoginByMobileRequest request) {
        UserEntity userEntity = userInfoMapper.findUserByPhone(request.getMobile());
        /**判断用户是否存在*/
        BaseResponse userIsExistByMobileResponse = userIsExistByMobile(userEntity);
        if (!userIsExistByMobileResponse.isSuccess()) {
            return userIsExistByMobileResponse;
        }

        /**判断密码是否正确 正确*/
        if (request.getPasswordMD5().equals(userEntity.getPassword())) {
            userEntity.setToken(TokenUtils.getToken(userEntity));
            userEntity.setPassword(null);
            return BaseResponse.createSuccessResult(userEntity,BaseErrorCodeEnum.LOGIN_STATUS_1);
        }
        /**错误*/
        else {
            return BaseResponse.createFailResult(BaseErrorCodeEnum.LOGIN_STATUS_3);
        }
    }

    @Override
    public BaseResponse loginByOther(LoginByOtherRequest request) {
        /**qq登陆*/
        if (AuthTypeEnum.AUTH_TYPE_ENUM_2.getValue().equals(request.getAuthType())) {
            UserEntity user = userInfoMapper.findUserByQqOpenId(request.getOpenid());
            return autoRegister(request, user);
        }
        /**微信登陆*/
        if (AuthTypeEnum.AUTH_TYPE_ENUM_3.getValue().equals(request.getAuthType())) {
            UserEntity user = userInfoMapper.findUserByWeChatOpenId(request.getOpenid());
            return autoRegister(request, user);
        }
        return null;
    }

    /**
     * 自动注册流程
     */
    private BaseResponse autoRegister(LoginByOtherRequest request, UserEntity user) {
        /**用户不存在 自动注册*/
        if (null == user) {
            UserEntity userEntity = loginRequestToUserEntity(request);
            /**注册成功*/
            if (1 == userInfoMapper.insertUser(userEntity)) {
                return buildLoginSuccessResponse(userEntity);
            } else {
                return BaseResponse.createFailResult(BaseErrorCodeEnum.LOGIN_STATUS_5);
            }
        }
        /**用户存在正常登陆*/
        else {
            return buildLoginSuccessResponse(user);
        }
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
        /**验证码校验*/
        BaseResponse verificationCodeCheckResponse = verificationCodeCheck(forgetPasswordRequest.getCode(), forgetPasswordRequest.getMobile(), forgetPasswordRequest.getZone());
        if (!verificationCodeCheckResponse.isSuccess()) {
            return verificationCodeCheckResponse;
        }
        /**新密码与旧密码相同*/
        if (forgetPasswordRequest.getPasswordMD5().equals(userEntity.getPassword())) {
            return BaseResponse.createFailResult(BaseErrorCodeEnum.UPDATE_STATUS_1);
        }
        userEntity.setPassword(forgetPasswordRequest.getPasswordMD5());
        if (1 == userInfoMapper.updateUserByPhone(userEntity)) {
            return BaseResponse.createSuccessResult(null,BaseErrorCodeEnum.UPDATE_STATUS_2);
        } else {
            return BaseResponse.createFailResult(BaseErrorCodeEnum.UPDATE_STATUS_3);
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
    public BaseResponse modifyUserInfo(UpdateUserInfoRequest updateUserInfoRequest) {
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
