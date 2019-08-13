package com.ysc.device.service.domain.entities;

import lombok.Data;

@Data
public class BaseEntity {
    /**
     *创建时间
     * */
    private String createTime;

    /**
     *更新时间
     * */
    private String updateTime;

    /**
     *token
     * */
    private String token;
}
