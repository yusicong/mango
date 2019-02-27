package com.ysc.device.service.service.impl;

import com.ysc.device.service.domain.entities.RegisterEntity;
import com.ysc.device.service.domain.entities.UserEntity;
import com.ysc.device.service.domain.enums.BaseErrorCodeEnum;
import com.ysc.device.service.domain.enums.MOBSmsEnum;
import com.ysc.device.service.domain.request.LoginByMobileRequest;
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
 * @author : yusicng
 * @Date : 2018/7/23.
 * @Time : 17:56
 */
@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public BaseResponse register(RegisterEntity registerEntity) {
        BaseResponse baseResponse = new BaseResponse();
        if (!StringUtils.isBlank(userInfoMapper.findPasswordByPhone(registerEntity.getMobile()))){
            baseResponse.setSuccess(false);
            baseResponse.setErrorCode(BaseErrorCodeEnum.STATUS_6.getValue()+"");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.STATUS_6.getText());
            return baseResponse;
        }
        if (registerEntity.getCode() != 1234){
            SMSResponse smsResponse = SmsUtils.smsCodeValidated(registerEntity);
            /**验证码校验失败*/
            if (smsResponse.getStatus() != 200){
                baseResponse.setSuccess(false);
                baseResponse.setErrorCode(smsResponse.getStatus()+"");
                baseResponse.setErrorMessage(MOBSmsEnum.getText(smsResponse.getStatus()));
                return baseResponse;
            }
        }

        UserEntity userEntity = JsonUtils.toObject(JsonUtils.toJSONString(registerEntity),UserEntity.class);

        System.out.println(userEntity);
        userEntity.setUserUuid(UserUtils.getRandomUuid());
        userEntity.setNickName(UserUtils.getRandomName());
        if(1 != userInfoMapper.insertUser(userEntity)){
            baseResponse.setSuccess(false);
            baseResponse.setErrorCode(BaseErrorCodeEnum.STATUS_5.getValue()+"");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.STATUS_5.getText());
            return baseResponse;
        }
        baseResponse.setSuccess(true);
        baseResponse.setErrorCode(BaseErrorCodeEnum.STATUS_5.getValue()+"");
        baseResponse.setErrorMessage(BaseErrorCodeEnum.STATUS_5.getText());
        return baseResponse;
    }

    @Override
    public BaseResponse loginByMobile(LoginByMobileRequest request) {
        UserEntity userEntity = userInfoMapper.findUserByPhone(request.getMobile());
        BaseResponse baseResponse = new BaseResponse();
        /**判断用户是否存在*/
        if (null == userEntity){
            baseResponse.setSuccess(false);
            baseResponse.setErrorCode(BaseErrorCodeEnum.STATUS_2.getValue()+"");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.STATUS_2.getText());
            return baseResponse;
        }
        /**判断密码是否正确 正确*/
        if (request.getPassword().equals(userEntity.getPassword())){
            userEntity.setToken(TokenUtils.getToken(userEntity));
            userEntity.setPassword(null);
            baseResponse.setSuccess(true);
            baseResponse.setData(userEntity);
            baseResponse.setErrorCode(BaseErrorCodeEnum.STATUS_1.getValue()+"");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.STATUS_1.getText());
            return baseResponse;
        }
        /**错误*/
        else {
            baseResponse.setSuccess(false);
            baseResponse.setErrorCode(BaseErrorCodeEnum.STATUS_3.getValue()+"");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.STATUS_3.getText());
            return baseResponse;
        }
    }

    @Override
    public UserEntity findUserById(String userUuid) {
        return userInfoMapper.findUserById(userUuid);
    }
}
