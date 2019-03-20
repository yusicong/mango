package com.ysc.device.service.service.impl.abs;

import com.ysc.device.service.domain.entities.UserEntity;
import com.ysc.device.service.domain.enums.ErrorCodeEnum;
import com.ysc.device.service.domain.response.BaseResponse;
import com.ysc.device.service.repository.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AbsServiceImpl {

    @Autowired
    protected UserInfoMapper userInfoMapper;

    public BaseResponse userinfoIsDifferent(UserEntity userEntity_1){


        /**判断手机号相同*/
        if ( null != userInfoMapper.findUserByPhone(userEntity_1.getMobile())){
            return BaseResponse.createFailResult(ErrorCodeEnum.UPDATE_STATUS_4);
        }
        /**判断qq号相同*/
        if (null != userInfoMapper.findUserByQqOpenId(userEntity_1.getQqOpenId())){
            return BaseResponse.createFailResult(ErrorCodeEnum.UPDATE_STATUS_5);
        }
        /**判断微信号相同*/
        if (null != userInfoMapper.findUserByWeChatOpenId(userEntity_1.getWechatOpenId())){
            return BaseResponse.createFailResult(ErrorCodeEnum.UPDATE_STATUS_6);
        }
        return BaseResponse.createSuccessResult("");
    }
}
