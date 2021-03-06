package com.ysc.device.service.domain.request;

import lombok.Data;

/**
 * Create by idea
 * Date 2019/7/17 14:50
 * @author yusicong
 */
@Data
public class SyncDataRequestBody{
    /**
     * 本条数据唯一标识
     */
    private String uuid;

    /**
     * 更新数据内容
     */
    private String content;
}
