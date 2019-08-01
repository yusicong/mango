package com.ysc.device.service.domain.enums;

import com.ysc.device.service.exception.BaseExceptionCode;
/**
 * @author yusicong
 */
public enum ErrorCodeEnum implements BaseExceptionCode {
    SYStem_ERROR_1("60001","无token"),
    SYStem_ERROR_2("60002","token获取失败"),
    SYStem_ERROR_3("60003","token验证失败"),

    LOGIN_STATUS_1("10001", "登录成功"),
    LOGIN_STATUS_2("20001", "用户不存在"),
    LOGIN_STATUS_3("20002", "密码错误"),
    LOGIN_STATUS_4("20003", "登录失败，登陆超时"),
    LOGIN_STATUS_5("20004", "登录失败，未知原因"),

    UPDATE_STATUS_1("40001", "新密码与旧密码相同"),
    UPDATE_STATUS_2("40002", "修改成功"),
    UPDATE_STATUS_3("40003", "修改失败，未知原因"),
    UPDATE_STATUS_4("40004", "修改失败，手机号已存在"),
    UPDATE_STATUS_5("40005", "修改失败，QQ号已存在"),
    UPDATE_STATUS_6("40006", "修改失败，微信号已存在"),

    QUERY_STATUS_1("50001", "没有匹配的结果"),

    REGISTER_STATUS_1("10002", "注册成功"),
    REGISTER_STATUS_2("30001", "注册失败，未知原因"),
    REGISTER_STATUS_3("30002", "注册失败，手机号已存在");

    private String errorCode;
    private String errorMessage;

    ErrorCodeEnum(String code, String message) {
        this.errorCode = code;
        this.errorMessage = message;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getErrorTips() {
        return null;
    }
}
