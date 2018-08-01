package com.ysc.device.service.domain.enums;

public enum RedisKeyPrefixEnum {

    DEVICE_1("VM:SERVICE:DEVICE_1"),

    DEVICE_2("VM:SERVICE:DEVICE_2");

    RedisKeyPrefixEnum(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
