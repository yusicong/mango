
package com.ysc.device.service.service.impl;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.ysc.device.service.domain.dto.uploadImgDTO;
import com.ysc.device.service.domain.response.BaseResponse;
import com.ysc.device.service.service.FileService;
import com.ysc.device.service.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    COSClient cosClient;

    @Override
    public BaseResponse uploadAvatar(HttpServletRequest httpServletRequest) {
        BaseResponse baseResponse = new BaseResponse();
        List<MultipartFile> files = ((MultipartHttpServletRequest) httpServletRequest).getFiles("file");
//        for (MultipartFile multipartFile : files) {
//
//        }
        Map<String, String[]> map = httpServletRequest.getParameterMap();

        String[] nameList = map.get("name");
        String[] fileNameList = map.get("fileName");
        String[] mimeTypeList = map.get("mimeType");
        String[] dataList = new String[20];
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            if ("data".equals(entry.getKey())){
                dataList = entry.getValue();
                System.out.println(JsonUtils.toJSONString(dataList));
            }
        }

        List<uploadImgDTO> uploadImgDTOS = JsonUtils.toObject(dataList[0],List.class);
        System.out.println(JsonUtils.toJSONString(uploadImgDTOS));

        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            String filePath = "C://123//";
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + file.getOriginalFilename())));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    baseResponse.setErrorMessage("第 " + i + " 个文件上传失败  ==> " + e.getMessage());
                    return baseResponse;
                }
            } else {
                baseResponse.setErrorMessage("第 " + i + " 个文件上传失败因为文件为空");
                return baseResponse;
            }
        }
        return baseResponse.success("");
    }


    @Override
    public BaseResponse uploadFile(Map<String ,File> fileMap) {

        // 指定文件所在的存储桶
        String bucketName = "mgyxz-image";
        try{
            for (Map.Entry<String, File> entry : fileMap.entrySet()) {
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, entry.getKey(), entry.getValue());
                PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
                log.info("putObjectResult :{}",JsonUtils.toJSONString(putObjectResult));
            }
        }catch (Exception e){
                log.info("uploadFile error:{}",e);
        }
        return null;
    }
}
