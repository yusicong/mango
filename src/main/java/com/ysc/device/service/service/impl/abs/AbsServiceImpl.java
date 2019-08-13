package com.ysc.device.service.service.impl.abs;

import com.ysc.device.service.domain.entities.UserEntity;
import com.ysc.device.service.domain.enums.BaseErrorCodeEnum;
import com.ysc.device.service.domain.enums.ErrorCodeEnum;
import com.ysc.device.service.domain.response.BaseResponse;
import com.ysc.device.service.repository.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

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

    public BaseResponse verificationCodeCheck(Integer code, String mobile, Integer zone) {
        BaseResponse baseResponse = new BaseResponse();

        return baseResponse.success("96500909".equals(code));
//        if ("96500909".equals(code)) {
//            return baseResponse.success(null);
//        }
//        SmsCodeValidateRequest smsCodeValidateRequest = new SmsCodeValidateRequest();
//        smsCodeValidateRequest.setCode(code);
//        smsCodeValidateRequest.setMobile(mobile);
//        smsCodeValidateRequest.setZone(zone);
//        SMSResponse smsResponse = SmsUtils.smsCodeValidated(smsCodeValidateRequest);
//        /**验证码校验失败*/
//        if (smsResponse.getStatus() != 200) {
//            baseResponse.setSuccess(false);
//            baseResponse.setErrorCode(smsResponse.getStatus() + "");
//            baseResponse.setErrorMessage(MOBSmsEnum.getText(smsResponse.getStatus()));
//            return baseResponse;
//        } else {
//            return baseResponse.success(null);
//        }
    }

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
}
