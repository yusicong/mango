package com.ysc.device.service.service.impl;


import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import com.ysc.device.service.domain.response.BaseResponse;
import com.ysc.device.service.service.MiPushService;
import groovy.util.logging.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Create by yusicong
 * Date 2019/3/8 15:19
 */
@Slf4j
@Service
public class MiPushServiceImpl implements MiPushService {
    @Override
    public BaseResponse sendMessage() {
        Constants.useOfficial();
        Sender sender = new Sender("Kmp2K5dyFbtQrDqipCPDrw==");
        String messagePayload = "This is a message";
        String title = "notification title";
        String description = "notification description";
        String useraccount = "f6c4c04f-3399-4f79-82ba-f46f786e80b7";    //useraccount非空白, 不能包含逗号, 长度小于128
        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(messagePayload)
                .restrictedPackageName("com.mango.mgyxz")
                .notifyType(1)     // 使用默认提示音提示
                .build();
        try {
            Result result = sender.sendToUserAccount(message, useraccount, 3); //根据useraccount, 发送消息到指定设备上
            return BaseResponse.createSuccessResult(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
