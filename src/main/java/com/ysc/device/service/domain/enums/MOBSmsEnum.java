package com.ysc.device.service.domain.enums;
/**
 * @author yusicong
 */

public enum MOBSmsEnum implements DictEnum {

    STATUS_200(200, "验证成功"),
    STATUS_405(405, "AppKey为空"),
    STATUS_406(406, "AppKey无效"),
    STATUS_456(456, "国家代码或手机号码为空"),
    STATUS_457(457, "手机号码格式错误"),
    STATUS_466(466, "请求校验的验证码为空"),
    STATUS_467(467, "请求校验验证码频繁（5分钟内同一个appkey的同一个号码最多只能校验三次）"),
    STATUS_468(468, "验证码错误"),
    STATUS_474(474, "没有打开服务端验证开关");
    /**
     * 获取值
     */
    private Integer value;

    /**
     * 获取Text
     */
    private String text;


    MOBSmsEnum(Integer value, String text) {
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
        for (MOBSmsEnum type : MOBSmsEnum.values()) {
            if (value.equals(type.getValue())) {
                return type.getText();
            }
        }
        return "--";
    }

    public static MOBSmsEnum of(Integer value) {
        for (MOBSmsEnum type : MOBSmsEnum.values()) {
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
