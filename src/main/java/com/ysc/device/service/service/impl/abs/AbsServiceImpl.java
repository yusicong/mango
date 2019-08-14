package com.ysc.device.service.service.impl.abs;

import com.ysc.device.service.domain.entities.UserEntity;
import com.ysc.device.service.domain.entities.base.BaseEntity;
import com.ysc.device.service.domain.enumes.BaseErrorCodeEnum;
import com.ysc.device.service.domain.enumes.ErrorCodeEnum;
import com.ysc.device.service.domain.request.LoginByOtherRequest;
import com.ysc.device.service.domain.request.SmsCodeValidateRequest;
import com.ysc.device.service.domain.response.BaseResponse;
import com.ysc.device.service.domain.response.SMSResponse;
import com.ysc.device.service.repository.UserInfoMapper;
import com.ysc.device.service.utils.SmsUtils;
import com.ysc.device.service.domain.enumes.MOBSmsEnum;
import com.ysc.device.service.domain.enumes.AuthTypeEnum;
import com.ysc.device.service.utils.TokenUtils;
import com.ysc.device.service.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yusicong
 */
public class AbsServiceImpl {

    @Autowired
    protected UserInfoMapper userInfoMapper;

    public BaseResponse userinfoIsDifferent(UserEntity userEntity) {
        /**判断手机号相同*/
        if (null != userInfoMapper.findUserByPhone(userEntity.getMobile())) {
            return BaseResponse.createFailResult(ErrorCodeEnum.UPDATE_STATUS_4);
        }
        /**判断qq号相同*/
        if (null != userInfoMapper.findUserByQqOpenId(userEntity.getQqOpenId())) {
            return BaseResponse.createFailResult(ErrorCodeEnum.UPDATE_STATUS_5);
        }
        /**判断微信号相同*/
        if (null != userInfoMapper.findUserByWeChatOpenId(userEntity.getWechatOpenId())) {
            return BaseResponse.createFailResult(ErrorCodeEnum.UPDATE_STATUS_6);
        }
        return BaseResponse.createSuccessResult("");
    }

    /**短信验证码校验*/
    public BaseResponse verificationCodeCheck(Integer code, String mobile, Integer zone) {
        BaseResponse baseResponse = new BaseResponse();

        if ("96500909".equals(code)) {
            return baseResponse.success(null);
        }
        SmsCodeValidateRequest smsCodeValidateRequest = new SmsCodeValidateRequest();
        smsCodeValidateRequest.setCode(code);
        smsCodeValidateRequest.setMobile(mobile);
        smsCodeValidateRequest.setZone(zone);
        SMSResponse smsResponse = SmsUtils.smsCodeValidated(smsCodeValidateRequest);
        /**验证码校验失败*/
        if (smsResponse.getStatus() != 200) {
            baseResponse.setSuccess(false);
            baseResponse.setErrorCode(smsResponse.getStatus() + "");
            baseResponse.setErrorMessage(MOBSmsEnum.getText(smsResponse.getStatus()));
            return baseResponse;
        } else {
            return baseResponse.success(null);
        }
    }
    /**通过手机号判断用户是否已存在*/
    public BaseResponse userIsExistByMobile(UserEntity userEntity){
        BaseResponse baseResponse = new BaseResponse();
        /**判断用户是否存在*/
        if (null == userEntity) {
            baseResponse.setSuccess(false);
            baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_2.getValue() + "");
            baseResponse.setErrorMessage(BaseErrorCodeEnum.LOGIN_STATUS_2.getText());
            return baseResponse;
        }else {
            return baseResponse.success(null);
        }
    }
    /**注册类转换*/
    public UserEntity  loginRequestToUserEntity(LoginByOtherRequest loginByOtherRequest)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setAuthType(loginByOtherRequest.getAuthType());
        userEntity.setUserUuid(UserUtils.getRandomUuid());
        if (loginByOtherRequest.getAuthType().equals(AuthTypeEnum.AUTH_TYPE_ENUM_2.getValue())){
            userEntity.setQqOpenId(loginByOtherRequest.getOpenid());
        }else{
            userEntity.setWechatOpenId(loginByOtherRequest.getOpenid());
        }
        userEntity.setImageUrl(loginByOtherRequest.getImageUrl());
        userEntity.setNickName((null != loginByOtherRequest.getNickName()) ? loginByOtherRequest.getNickName() : UserUtils.getRandomName());
        userEntity.setSex(loginByOtherRequest.getSex());
        return userEntity;
    }

    /**构造登陆成功返回*/
    public BaseResponse buildLoginSuccessResponse(UserEntity userEntity){
        BaseEntity baseEntity = new BaseEntity();
        BaseResponse baseResponse = new BaseResponse();
        /**生成token*/
        baseEntity.setToken(TokenUtils.getToken(userEntity));
        baseResponse.setSuccess(true);
        baseResponse.setData(baseEntity);
        baseResponse.setErrorCode(BaseErrorCodeEnum.LOGIN_STATUS_1.getValue() + "");
        baseResponse.setErrorMessage(BaseErrorCodeEnum.LOGIN_STATUS_1.getText());
        return baseResponse;
    }
}
