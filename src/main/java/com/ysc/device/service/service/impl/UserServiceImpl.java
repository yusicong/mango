package com.ysc.device.service.service.impl;

import com.ysc.device.service.domain.entities.RegisterEntity;
import com.ysc.device.service.domain.entities.User;
import com.ysc.device.service.domain.entities.UserEntity;
import com.ysc.device.service.domain.enums.BaseErrorCodeEnum;
import com.ysc.device.service.domain.enums.MOBSmsEnum;
import com.ysc.device.service.domain.request.LoginByMboileRequest;
import com.ysc.device.service.domain.response.BaseResponse;
import com.ysc.device.service.domain.response.SMSResponse;
import com.ysc.device.service.repository.UserInfoMapper;
import com.ysc.device.service.service.UserService;
import com.ysc.device.service.utils.SmsUtils;
import com.ysc.device.service.utils.TokenUtils;
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
    public User findUserById(String Id) {
        return userInfoMapper.findUserById(Id);
    }

    @Override
    public User findByUsername(User user) {
        return userInfoMapper.findByUsername(user.getUsername());
    }

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
        if(1 != userInfoMapper.insertUser(registerEntity)){
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
    public BaseResponse loginByMboile(LoginByMboileRequest request) {
        UserEntity userEntity = userInfoMapper.findUserByPhone(request.getMoblie());
        BaseResponse baseResponse = new BaseResponse();
        /**判断密码是否正确 正确*/
        if (request.getPassword().equals(userEntity.getPassword())){
            userEntity.setToken(TokenUtils.getToken(userEntity));
            baseResponse.setSuccess(true);
            baseResponse.setModel(userEntity);
            baseResponse.setErrorCode(BaseErrorCodeEnum.STATUS_1.getValue()+"");
            baseResponse.setErrorCode(BaseErrorCodeEnum.STATUS_1.getText());
        }
        /**错误*/
        else {
            baseResponse.setSuccess(false);
            baseResponse.setErrorCode(BaseErrorCodeEnum.STATUS_3.getValue()+"");
            baseResponse.setErrorCode(BaseErrorCodeEnum.STATUS_3.getText());
        }
        return baseResponse;
    }
}
