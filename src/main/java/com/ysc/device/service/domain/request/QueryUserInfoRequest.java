package com.ysc.device.service.domain.request;

import lombok.Data;

/**
 * @author yusicong
 */
@Data
public class QueryUserInfoRequest{


    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户uuid
     */
    private String userUuid;

    /**
     * 用户qqid
     */
    private String qqOpenId;

    /**
     * 用户微信id
     */
    private String wechatOpenId;
}
