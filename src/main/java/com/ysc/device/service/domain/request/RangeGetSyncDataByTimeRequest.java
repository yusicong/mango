package com.ysc.device.service.domain.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 码字数据查询入参
 * @author enmonster
 * */
@Data
public class RangeGetSyncDataByTimeRequest {

    /**
     * 用户id
     */
    private String userUuid;

    /**
     * 数据id
     */
    private String uuid;

    /**
     * 查询开始时间
     */
    private LocalDateTime beginTime;

    /**
     * 查询结束时间
     */
    private LocalDateTime endTime;

    private List<String> excludeUuidList;
}
