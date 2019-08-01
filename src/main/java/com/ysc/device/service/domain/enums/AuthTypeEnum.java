package com.ysc.device.service.domain.enums;
/**
 * @author enmonster
 * 用户登录类型
 */
public enum AuthTypeEnum implements DictEnum {

    AUTH_TYPE_ENUM_1(1, "mobile"),
    AUTH_TYPE_ENUM_2(2, "qq"),
    AUTH_TYPE_ENUM_3(3, "weChat");
    /**
     * 获取值
     */
    private Integer value;

    /**
     * 获取Text
     */
    private String text;


    AuthTypeEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getText() {
        return text;
    }

    public static String getText(Integer value) {
        for (AuthTypeEnum type : AuthTypeEnum.values()) {
            if (value.equals(type.getValue())) {
                return type.getText();
            }
        }
        return "--";
    }

    public static AuthTypeEnum of(Integer value) {
        for (AuthTypeEnum type : AuthTypeEnum.values()) {
            if (value.equals(type.getValue())) {
                return type;
            }
        }
        throw new IllegalArgumentException("invalid access control type value :" + value);
    }
    public static void main(String[] args) {
        System.out.println(getText(200));
    }
}
