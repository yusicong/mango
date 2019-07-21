package com.ysc.device.service.domain.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
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
