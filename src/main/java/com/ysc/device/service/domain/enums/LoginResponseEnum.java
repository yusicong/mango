package com.ysc.device.service.domain.enums;




public enum LoginResponseEnum implements DictEnum {

    STATUS_1(10001, "登录成功"),
    STATUS_2(20001, "登录失败，用户不存在"),
    STATUS_3(20002, "登录失败，密码错误"),
    STATUS_4(20003, "登录失败，登陆超时");
    /**
     * 获取值
     */
    private Integer value;

    /**
     * 获取Text
     */
    private String text;


    LoginResponseEnum(Integer value, String text) {
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
        for (LoginResponseEnum type : LoginResponseEnum.values()) {
            if (value.equals(type.getValue())) {
                return type.getText();
            }
        }
        return "--";
    }

    public static LoginResponseEnum of(Integer value) {
        for (LoginResponseEnum type : LoginResponseEnum.values()) {
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
