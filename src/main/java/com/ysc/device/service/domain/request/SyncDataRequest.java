package com.ysc.device.service.domain.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Create by idea
 * Date 2019/7/17 14:48
 * @author yusicong
 */
@Data
public class SyncDataRequest {
    /**
     * 用户唯一标识
     */
    @NotBlank
    private String userUuid;

    /**
     * 设备唯一标识
     */
    @NotBlank
    private String deviceUuid;

    /**
     * 上次同步时间
     */
    @NotNull
    private LocalDateTime syncTime;

    /**
     * 所需更新数据体
     */
    @Size(min = 1, max = 50)
    private List<SyncDataRequestBody> data;
}
