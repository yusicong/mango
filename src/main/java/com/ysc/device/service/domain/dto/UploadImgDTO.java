package com.ysc.device.service.domain.dto;

import lombok.Data;
/**
 * @author yusicong
 */
@Data
public class UploadImgDTO {

    private String userUuid;

    private String name;

    private String fileName;

    private String mimeType;

    private String fileType;

}
