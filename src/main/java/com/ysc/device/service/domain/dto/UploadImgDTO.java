package com.ysc.device.service.domain.dto;

import lombok.Data;
/**
 * @author enmonster
 */
@Data
public class UploadImgDTO {

    private String userUuid;

    private String name;

    private String fileName;

    private String mimeType;

    private String fileType;

}
