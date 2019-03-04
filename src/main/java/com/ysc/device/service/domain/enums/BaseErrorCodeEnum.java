package com.ysc.device.service.domain.enums;




public enum BaseErrorCodeEnum implements DictEnum {

    SYStem_ERROR_1(60001,"无token"),
    SYStem_ERROR_2(60002,"token获取失败"),
    SYStem_ERROR_3(60002,"token验证失败"),

    LOGIN_STATUS_1(10001, "登录成功"),
    LOGIN_STATUS_2(20001, "用户不存在"),
    LOGIN_STATUS_3(20002, "密码错误"),
    LOGIN_STATUS_4(20003, "登录失败，登陆超时"),
    LOGIN_STATUS_5(20004, "登录失败，未知原因"),

    UPDATE_STATUS_1(40001, "新密码与旧密码相同"),
    UPDATE_STATUS_2(40002, "修改成功"),
    UPDATE_STATUS_3(40002, "修改失败，未知原因"),

    QUERY_STATUS_1(50001, "没有匹配的结果"),

    REGISTER_STATUS_1(10002, "注册成功"),
    REGISTER_STATUS_2(30001, "注册失败，未知原因"),
    REGISTER_STATUS_3(30002, "注册失败，手机号已存在");
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
