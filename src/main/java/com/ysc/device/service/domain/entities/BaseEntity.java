package com.ysc.device.service.domain.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {

    /**
     *创建时间
     * */
    private LocalDateTime createTime;

    /**
     *更新时间
     * */
    private LocalDateTime updateTime;

    /**
     *token
     * */
    private String token;
}
