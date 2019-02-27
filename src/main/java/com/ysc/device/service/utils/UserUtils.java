package com.ysc.device.service.utils;

import java.util.UUID;

public class UserUtils {

    private static final String NICKNAME = "小芒果";

    public static String getRandomName(){
        return NICKNAME+System.currentTimeMillis();
    }

    public static String getRandomUuid(){
        return UUID.randomUUID().toString();
    }
}
