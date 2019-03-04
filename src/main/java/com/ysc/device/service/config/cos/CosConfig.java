package com.ysc.device.service.config.cos;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.region.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CosConfig {

    @Value("${config.cos.secretId}")
    private String secretId;

    @Value("${config.cos.secretKey}")
    private String secretKey;

    @Value("${config.cos.regionName}")
    private String endPoint;

    private final static Logger logger = LoggerFactory.getLogger(CosConfig.class);
    @Bean
    public COSClient COSClient(){
        try{
            // 1 初始化用户身份信息（secretId, secretKey）。
            COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
            // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
            // clientConfig中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者接口文档 FAQ 中说明。
            ClientConfig clientConfig = new ClientConfig(new Region(endPoint));
            // 3 生成 cos 客户端。
            COSClient cosClient = new COSClient(cred, clientConfig);
            logger.info("读取到结果为：{}",secretId+secretKey+endPoint);
            return cosClient;
        }catch (CosServiceException e){
            logger.error("cos operate Fail,CosServiceException:{},", e);
        }catch (Exception e){
            logger.error("cos operate Fail,CosServiceException:{},", e);
        }
        return null;
    }
}
