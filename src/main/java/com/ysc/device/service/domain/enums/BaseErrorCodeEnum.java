package com.ysc.device.service.domain.enums;




public enum BaseErrorCodeEnum implements DictEnum {

    STATUS_1(10001, "登录成功"),
    STATUS_2(20001, "登录失败，用户不存在"),
    STATUS_3(20002, "登录失败，密码错误"),
    STATUS_4(20003, "登录失败，登陆超时"),
    STATUS_5(30001, "注册失败，未知原因"),
    STATUS_6(30002, "注册失败，手机号已存在");
    /**
     * 获取值
     */
    private Integer value;

    /**
     * 获取Text
     */
    private String text;


    BaseErrorCodeEnum(Integer value, String text) {
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
        for (BaseErrorCodeEnum type : BaseErrorCodeEnum.values()) {
            if (value.equals(type.getValue())) {
                return type.getText();
            }
        }
        return "--";
    }

    public static BaseErrorCodeEnum of(Integer value) {
        for (BaseErrorCodeEnum type : BaseErrorCodeEnum.values()) {
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
