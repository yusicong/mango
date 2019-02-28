package com.ysc.device.service.service.impl;

import com.ysc.device.service.domain.dto.RegisterDTO;
import com.ysc.device.service.domain.entities.BaseEntity;
import com.ysc.device.service.domain.entities.UserEntity;
import com.ysc.device.service.domain.enums.AuthTypeEnum;
import com.ysc.device.service.domain.enums.BaseErrorCodeEnum;
import com.ysc.device.service.domain.enums.MOBSmsEnum;
import com.ysc.device.service.domain.request.LoginByMobileRequest;
import com.ysc.device.service.domain.request.LoginByOtherRequest;
import com.ysc.device.service.domain.response.BaseResponse;
import com.ysc.device.service.domain.response.SMSResponse;
import com.ysc.device.service.repository.UserInfoMapper;
import com.ysc.device.service.service.UserService;
import com.ysc.device.service.utils.JsonUtils;
import com.ysc.device.service.utils.SmsUtils;
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
public class UserServiceImpl implements UserService
{

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public BaseResponse register(RegisterDTO registerDTO) {
        BaseResponse baseResponse = new BaseResponse();
        if (!StringUtils.isBlank(userInfoMapper.findPasswordByPhone(registerDTO.getMobile()))){
            baseResponse.setSuccess(false);
            baseResponse.setErrorCode(BaseErrorCodeEnum.REGISTER_STATUS_2.getValue()+"");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.REGISTER_STATUS_2.getText());
            return baseResponse;
        }
        if (registerDTO.getCode() != 1234){
            SMSResponse smsResponse = SmsUtils.smsCodeValidated(registerDTO);
            /**验证码校验失败*/
            if (smsResponse.getStatus() != 200){
                baseResponse.setSuccess(false);
                baseResponse.setErrorCode(smsResponse.getStatus()+"");
                baseResponse.setErrorMessage(MOBSmsEnum.getText(smsResponse.getStatus()));
                return baseResponse;
            }
        }

        UserEntity userEntity = JsonUtils.toObject(JsonUtils.toJSONString(registerDTO),UserEntity.class);
        userEntity.setUserUuid(UserUtils.getRandomUuid());
        userEntity.setNickName(UserUtils.getRandomName());
        if(1 != userInfoMapper.insertUser(userEntity)){
            baseResponse.setSuccess(false);
            baseResponse.setErrorCode(BaseErrorCodeEnum.REGISTER_STATUS_2.getValue()+"");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.REGISTER_STATUS_2.getText());
            return baseResponse;
        }
        baseResponse.setSuccess(true);
        baseResponse.setErrorCode(BaseErrorCodeEnum.REGISTER_STATUS_1.getValue()+"");
        baseResponse.setErrorMessage(BaseErrorCodeEnum.REGISTER_STATUS_1.getText());
        return baseResponse;
    }

    @Override
    public BaseResponse loginByMobile(LoginByMobileRequest request) {
        UserEntity userEntity = userInfoMapper.findUserByPhone(request.getMobile());
        BaseResponse baseResponse = new BaseResponse();
        /**判断用户是否存在*/
        if (null == userEntity){
            baseResponse.setSuccess(false);
            baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_2.getValue()+"");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.LOGIN_STATUS_2.getText());
            return baseResponse;
        }
        /**判断密码是否正确 正确*/
        if (request.getPassword().equals(userEntity.getPassword())){
            userEntity.setToken(TokenUtils.getToken(userEntity));
            userEntity.setPassword(null);
            baseResponse.setSuccess(true);
            baseResponse.setData(userEntity);
            baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_1.getValue()+"");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.LOGIN_STATUS_1.getText());
            return baseResponse;
        }
        /**错误*/
        else {
            baseResponse.setSuccess(false);
            baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_3.getValue()+"");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.LOGIN_STATUS_3.getText());
            return baseResponse;
        }
    }

    @Override
    public BaseResponse loginByOther(LoginByOtherRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        /**qq登陆*/
        if (AuthTypeEnum.AUTH_TYPE_ENUM_2.getValue() == request.getAuthType()){
            /**用户不存在 自动注册*/
            UserEntity user = userInfoMapper.findUserByQqOpenId(request.getOpenid());
            if (null == user){
                UserEntity userEntity = new UserEntity();
                userEntity.setAuthType(request.getAuthType());
                userEntity.setUserUuid(UserUtils.getRandomUuid());
                userEntity.setQqOpenId(request.getOpenid());
                userEntity.setImageUrl(null != request.getImageUrl() ? request.getImageUrl() : null);
                userEntity.setNickName(null != request.getNickName() ? request.getNickName():UserUtils.getRandomName() );
                userEntity.setSex(null != request.getSex() ? request.getSex() : null);
                /**注册成功*/
                if(1 == userInfoMapper.insertUser(userEntity)){
                    BaseEntity baseEntity = new BaseEntity();
                    /**生成token*/
                    baseEntity.setToken(TokenUtils.getToken(userEntity));
                    baseResponse.setSuccess(true);
                    baseResponse.setData(baseEntity);
                    baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_1.getValue()+"");
                    baseResponse.setErrorMessage(BaseErrorCodeEnum.LOGIN_STATUS_1.getText());
                    return baseResponse;
                }else {
                    baseResponse.setSuccess(false);
                    baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_5.getValue()+"");
                    baseResponse.setErrorMessage(BaseErrorCodeEnum.LOGIN_STATUS_5.getText());
                    return baseResponse;
                }
            }
            /**用户存在正常登陆*/
            else{
                System.out.println("用户存在");
                BaseEntity baseEntity = new BaseEntity();
                /**生成token*/
                baseEntity.setToken(TokenUtils.getToken(user));
                baseResponse.setSuccess(true);
                baseResponse.setData(baseEntity);
                baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_1.getValue()+"");
                baseResponse.setErrorMessage(BaseErrorCodeEnum.LOGIN_STATUS_1.getText());
                return baseResponse;
            }
        }

        /**微信登陆*/
        if (AuthTypeEnum.AUTH_TYPE_ENUM_3.getValue() == request.getAuthType()){
            /**用户不存在 自动注册*/
            UserEntity user = userInfoMapper.findUserByWeChatOpenId(request.getOpenid());
            if (null == user){
                UserEntity userEntity = new UserEntity();
                userEntity.setAuthType(request.getAuthType());
                userEntity.setUserUuid(UserUtils.getRandomUuid());
                userEntity.setWechatOpenId(request.getOpenid());
                userEntity.setImageUrl(null != request.getImageUrl() ? request.getImageUrl() : null);
                userEntity.setNickName(null != request.getNickName() ? request.getNickName():UserUtils.getRandomName() );
                userEntity.setSex(null != request.getSex() ? request.getSex() : null);
                /**注册成功*/
                if(1 == userInfoMapper.insertUser(userEntity)){
                    BaseEntity baseEntity = new BaseEntity();
                    /**生成token*/
                    baseEntity.setToken(TokenUtils.getToken(userEntity));
                    baseResponse.setSuccess(true);
                    baseResponse.setData(baseEntity);
                    baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_1.getValue()+"");
                    baseResponse.setErrorMessage(BaseErrorCodeEnum.LOGIN_STATUS_1.getText());
                    return baseResponse;
                }else {
                    baseResponse.setSuccess(false);
                    baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_5.getValue()+"");
                    baseResponse.setErrorMessage(BaseErrorCodeEnum.LOGIN_STATUS_5.getText());
                    return baseResponse;
                }
            }
            /**用户存在正常登陆*/
            else{
                System.out.println("用户存在");
                BaseEntity baseEntity = new BaseEntity();
                /**生成token*/
                baseEntity.setToken(TokenUtils.getToken(user));
                baseResponse.setSuccess(true);
                baseResponse.setData(baseEntity);
                baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_1.getValue()+"");
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
}
