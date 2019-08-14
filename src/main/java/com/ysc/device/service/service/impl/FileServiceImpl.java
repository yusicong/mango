
package com.ysc.device.service.service.impl;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.ysc.device.service.domain.dto.UploadImgDTO;
import com.ysc.device.service.domain.enumes.BaseErrorCodeEnum;
import com.ysc.device.service.domain.response.BaseResponse;
import com.ysc.device.service.service.FileService;
import com.ysc.device.service.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yusicong
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    COSClient cosClient;

    @Value("${config.cos.url}")
    private String cosUrl;

    @Override
    public BaseResponse upload(HttpServletRequest httpServletRequest) {
        BaseResponse baseResponse = new BaseResponse();
        Map<String, String[]> map = httpServletRequest.getParameterMap();
        List<UploadImgDTO> uploadImgDTOList = new ArrayList<>();

        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            if ("data".equals(entry.getKey())) {
                for (String dataDTO : entry.getValue()) {
                    uploadImgDTOList = JsonUtils.toObject(dataDTO, List.class);
                    if (null ==uploadImgDTOList || uploadImgDTOList.isEmpty()){
                        baseResponse.setSuccess(false);
                        baseResponse.setErrorCode(BaseErrorCodeEnum.SYStem_ERROR_4.getValue()+"");
                        baseResponse.setErrorMessage(BaseErrorCodeEnum.SYStem_ERROR_4.getText());
                        return baseResponse;
                    }
                    System.out.println(JsonUtils.toJSONString(uploadImgDTOList));
                    Map<String, File> fileMap = new HashMap<>();
                    for (int i = 0 ; i < uploadImgDTOList.size() ; i++){
                        UploadImgDTO uploadImgDTO = JsonUtils.toObject(JsonUtils.toJSONString(uploadImgDTOList.get(i)),UploadImgDTO.class);

                        if (StringUtils.isBlank(uploadImgDTO.getMimeType()) ||
                            StringUtils.isBlank(uploadImgDTO.getFileName()) ||
                            StringUtils.isBlank(uploadImgDTO.getFileType()) ||
                            StringUtils.isBlank(uploadImgDTO.getUserUuid()) ||
                            StringUtils.isBlank(uploadImgDTO.getName())){
                            baseResponse.setSuccess(false);
                            baseResponse.setErrorCode(BaseErrorCodeEnum.SYStem_ERROR_4.getValue()+"");
                            baseResponse.setErrorMessage(BaseErrorCodeEnum.SYStem_ERROR_4.getText());
                            return baseResponse;
                        }

                        MultipartFile multipartFile = ((MultipartHttpServletRequest) httpServletRequest).getFile(uploadImgDTO.getName());
                        if (null != multipartFile){
                            try {
                                /**生成临时文件用以把multipartFile转为File*/
                                final File excelFile = File.createTempFile(uploadImgDTO.getFileName(),uploadImgDTO.getMimeType());
                                multipartFile.transferTo(excelFile);
                                fileMap.put(uploadImgDTO.getUserUuid()+"/"+uploadImgDTO.getFileType()+"/"+uploadImgDTO.getFileName()+"."+uploadImgDTO.getMimeType(), excelFile);
                                /**删除临时文件*/
//                            excelFile.delete();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    /**未检测到文件*/
                    if (!fileMap.isEmpty()){
                        BaseResponse uploadFileBaseRes = uploadFile(fileMap);
                        if (uploadFileBaseRes.isSuccess()){
                            baseResponse.setSuccess(true);
                            uploadFileBaseRes.setErrorCode(BaseErrorCodeEnum.FILE_STATUS_1.getValue()+"");
                            uploadFileBaseRes.setErrorMessage(BaseErrorCodeEnum.FILE_STATUS_1.getText());
                            return uploadFileBaseRes;
                        }
                    }else {
                        baseResponse.setSuccess(false);
                        baseResponse.setErrorCode(BaseErrorCodeEnum.FILE_STATUS_2.getValue()+"");
                        baseResponse.setErrorMessage(BaseErrorCodeEnum.FILE_STATUS_2.getText());
                        return baseResponse;
                    }
                }
            }
        }
        baseResponse.setSuccess(false);
        baseResponse.setErrorCode(BaseErrorCodeEnum.FILE_STATUS_2.getValue()+"");
        baseResponse.setErrorMessage(BaseErrorCodeEnum.FILE_STATUS_2.getText());
        return baseResponse;
    }


    @Override
    public BaseResponse uploadFile(Map<String, File> fileMap) {
        // 指定文件所在的存储桶
        String bucketName = "mgyxz-image-1255371192";
        System.out.println(cosClient.getBucketLocation(bucketName));
        List<String> fileSrcList = new ArrayList<>();
        try {
            for (Map.Entry<String, File> entry : fileMap.entrySet()) {
                fileSrcList.add(cosUrl+entry.getKey());
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, entry.getKey(), entry.getValue());
                PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
                log.info("putObjectResult :{}", JsonUtils.toJSONString(putObjectResult));
            }
            return BaseResponse.createSuccessResult(fileSrcList);
        } catch (Exception e) {
            log.info("uploadFile error:{}", e);
            return BaseResponse.createFailResult(null,null);
        }

    }
}
